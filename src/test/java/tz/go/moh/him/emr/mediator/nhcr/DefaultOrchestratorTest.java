package tz.go.moh.him.emr.mediator.nhcr;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.*;
import org.openhim.mediator.engine.MediatorConfig;
import org.openhim.mediator.engine.messages.FinishRequest;
import org.openhim.mediator.engine.messages.MediatorSocketRequest;
import org.openhim.mediator.engine.testing.MockLauncher;
import org.openhim.mediator.engine.testing.TestingUtils;
import tz.go.moh.him.emr.mediator.nhcr.mock.MockDestination;
import tz.go.moh.him.emr.mediator.nhcr.orchestrator.DefaultOrchestrator;
import tz.go.moh.him.emr.mediator.nhcr.utils.TestUtils;

import java.io.File;
import java.io.InputStream;
import java.util.*;

public class DefaultOrchestratorTest {

    /**
     * The configuration.
     */
    private static MediatorConfig configuration;

    /**
     * The actor system.
     */
    private static ActorSystem system;

    /**
     * Runs cleanup after each test execution.
     */
    @After
    public void after() {
        system = ActorSystem.create();
    }

    /**
     * Runs initialization before each test execution.
     */
    @Before
    public void before() {
        List<MockLauncher.ActorToLaunch> actorsToLaunch = new LinkedList<>();

        actorsToLaunch.add(new MockLauncher.ActorToLaunch("mllp-connector", MockDestination.class));

        TestingUtils.launchActors(system, configuration.getName(), actorsToLaunch);
    }

    /**
     * Runs cleanup after class execution.
     */
    @AfterClass
    public static void afterClass() {
        TestingUtils.clearRootContext(system, configuration.getName());
        JavaTestKit.shutdownActorSystem(system);
        system = null;
    }

    /**
     * Runs initialization before each class execution.
     */
    @BeforeClass
    public static void beforeClass() {
        try {
            configuration = loadConfig(null);
            system = ActorSystem.create();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the mediator configuration.
     *
     * @param configPath The configuration path.
     * @return Returns the mediator configuration.
     */
    public static MediatorConfig loadConfig(String configPath) {
        MediatorConfig config = new MediatorConfig();

        try {
            if (configPath != null) {
                Properties props = new Properties();
                File conf = new File(configPath);
                InputStream in = FileUtils.openInputStream(conf);
                props.load(in);
                IOUtils.closeQuietly(in);

                config.setProperties(props);
            } else {
                config.setProperties("mediator.properties");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        config.setName(config.getProperty("mediator.name"));
        config.setServerHost(config.getProperty("mediator.host"));
        config.setServerPort(Integer.parseInt(config.getProperty("mediator.port")));
        config.setRootTimeout(Integer.parseInt(config.getProperty("mediator.timeout")));

        config.setCoreHost(config.getProperty("core.host"));
        config.setCoreAPIUsername(config.getProperty("core.api.user"));
        config.setCoreAPIPassword(config.getProperty("core.api.password"));

        config.setCoreAPIPort(Integer.parseInt(config.getProperty("core.api.port")));
        config.setHeartbeatsEnabled(true);

        return config;
    }

    @Test
    public void testMediatorSocketRequest() throws Exception {
        new JavaTestKit(system) {{
            final MediatorConfig testConfig = new MediatorConfig("emr-mediator-nhcr", "localhost", 3026);
            final ActorRef defaultOrchestrator = system.actorOf(Props.create(DefaultOrchestrator.class, testConfig));

            MediatorSocketRequest request = new MediatorSocketRequest(
                    getRef(),
                    getRef(),
                    "unit-test",
                    UUID.randomUUID().toString(),
                    null,
                    null,
                    TestUtils.getHL7TestMessage(),
                    false
            );

            defaultOrchestrator.tell(request, getRef());

            final Object[] out = new ReceiveWhile<Object>(Object.class, duration("3 seconds")) {
                @Override
                protected Object match(Object msg) {
                    if (msg instanceof FinishRequest) {
                        return msg;
                    }
                    throw noMatch();
                }
            }.get();

            Assert.assertTrue(Arrays.stream(out).anyMatch(c -> c instanceof FinishRequest));
        }};
    }
}
