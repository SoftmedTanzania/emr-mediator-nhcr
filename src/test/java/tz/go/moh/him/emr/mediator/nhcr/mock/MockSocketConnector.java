package tz.go.moh.him.emr.mediator.nhcr.mock;

import akka.actor.UntypedActor;
import org.openhim.mediator.engine.messages.MediatorSocketRequest;
import org.openhim.mediator.engine.messages.MediatorSocketResponse;

/**
 * Represents a mock socket connector.
 */
public abstract class MockSocketConnector extends UntypedActor {

    /**
     * The mock response.
     */
    public abstract String getResponse();

    /**
     * Executed when a message is received.
     *
     * @param request The request.
     */
    public abstract void executeOnReceive(MediatorSocketRequest request);

    /**
     * Executed when a message is received.
     *
     * @param message The message.
     */
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof MediatorSocketRequest) {
            executeOnReceive((MediatorSocketRequest) message);

            MediatorSocketResponse response = new MediatorSocketResponse((MediatorSocketRequest) message, getResponse());
            ((MediatorSocketRequest) message).getRespondTo().tell(response, getSelf());
        } else {
            unhandled(message);
        }
    }
}
