package tz.go.moh.him.emr.mediator.nhcr.mock;

import org.openhim.mediator.engine.messages.MediatorSocketRequest;

/**
 * Represents a mock destination.
 */
public class MockDestination extends MockSocketConnector {

    /**
     * Initializes a new instance of the {@link MockDestination} class.
     */
    public MockDestination() {
    }

    /**
     * Gets the response.
     *
     * @return Returns the response.
     */
    @Override
    public String getResponse() {
        return null;
    }

    /**
     * Handles the message.
     *
     * @param msg The message.
     */
    @Override
    public void executeOnReceive(MediatorSocketRequest msg) {

    }
}
