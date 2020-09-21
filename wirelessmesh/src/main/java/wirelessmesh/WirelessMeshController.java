package wirelessmesh;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class WirelessMeshController {

    @RequestMapping(value = "/get-state", method = POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Object> createProduct(@RequestBody String cmd) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        TestServiceGrpc.TestServiceBlockingStub stub
                = TestServiceGrpc.newBlockingStub(channel);

        Iterator<Deviceservice.State> state = stub.getState(Deviceservice.GetCommand.newBuilder()
                .setId(cmd)
                .build());
        String s = state.next().getState();
        channel.shutdown();
        return new ResponseEntity<>(s, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/set-state", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createProduct(@RequestBody Deviceservice.SetCommand cmd) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        TestServiceGrpc.TestServiceBlockingStub stub
                = TestServiceGrpc.newBlockingStub(channel);

        stub.setState(Deviceservice.SetCommand.newBuilder()
                .setId(cmd.getId())
                .setState(cmd.getState())
                .build());

        channel.shutdown();

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}