package wirelessmesh;

import io.cloudstate.springboot.starter.autoconfigure.EnableCloudstate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.cloudstate.javasupport.*;

import wirelessmesh.domain.*;

@EnableCloudstate
@SpringBootApplication
public class WirelessMeshSpringApplication {
    public static void main(String[] args) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException {
        SpringApplication.run(WirelessMeshSpringApplication.class, args);

        new CloudState()
                .registerEventSourcedEntity(
                        TestEntity.class,
                        Deviceservice.getDescriptor().findServiceByName("TestService"),
                        wirelessmesh.persistence.Domain.getDescriptor())
                .start()
                .toCompletableFuture()
                .get();
    }
}
