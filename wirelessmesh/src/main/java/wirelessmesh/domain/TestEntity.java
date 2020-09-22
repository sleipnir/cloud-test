package wirelessmesh.domain;

import io.cloudstate.javasupport.EntityId;
import io.cloudstate.javasupport.eventsourced.*;
import io.cloudstate.springboot.starter.CloudstateContext;
import io.cloudstate.springboot.starter.CloudstateEntityBean;
import wirelessmesh.Deviceservice;
import wirelessmesh.persistence.Domain;

/**
 * This represents the domain entity that will be the digital twin of one or our wireless mesh devices. We model this as closely
 * as possible to the real world behavior of a router owned by one of our customers.
 */
@EventSourcedEntity
@CloudstateEntityBean
public class TestEntity {

    private String state = "";

    /**
     * Unique identifier for this entity, also just happens to be the same as deviceId.
     */
    @EntityId
    private String id;

    @CloudstateContext
    private EventSourcedContext context;

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