package wirelessmesh.domain;

import com.google.protobuf.Empty;
import io.cloudstate.javasupport.EntityId;
import io.cloudstate.javasupport.eventsourced.*;
import io.cloudstate.javasupport.eventsourced.EventSourcedEntity;
import wirelessmesh.*;
import wirelessmesh.persistence.*;

/**
 * This represents the domain entity that will be the digital twin of one or our wireless mesh devices. We model this as closely
 * as possible to the real world behavior of a router owned by one of our customers.
 */
@EventSourcedEntity
public class TestEntity {
    /**
     * Unique identifier for this entity, also just happens to be the same as deviceId.
     */
    private final String id;

    private String state = "";

    /**
     * Constructor.
     * @param entityId The entity id will be the deviceId.
     */
    public TestEntity(@EntityId String entityId) {
        this.id = entityId;
    }

    @CommandHandler
    public Deviceservice.State getState(Deviceservice.GetCommand cmd, CommandContext ctx) {
        return Deviceservice.State.newBuilder().setState(this.state).build();
    }

    @CommandHandler
    public Deviceservice.Empty setState(Deviceservice.SetCommand cmd, CommandContext ctx) {
        ctx.emit(Domain.StateSet.newBuilder()
                .setId(cmd.getId())
                .setState(cmd.getState()).build());

        return Deviceservice.Empty.getDefaultInstance();
    }

    @EventHandler
    public void stateSetHandler(Domain.StateSet ss) {
        state = ss.getState();
    }

}