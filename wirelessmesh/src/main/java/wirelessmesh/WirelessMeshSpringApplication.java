package wirelessmesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.cloudstate.springboot.starter.autoconfigure.EnableCloudstate;

@EnableCloudstate
@SpringBootApplication
public class WirelessMeshSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(WirelessMeshSpringApplication.class, args);
    }
}
