package wirelessmesh;

import com.google.protobuf.Descriptors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wirelessmesh.persistence.Domain;

@Configuration
public class WirelessMeshConfiguration {

    @Bean
    public Descriptors.ServiceDescriptor testEntityServiceDescriptor() {
        return Deviceservice.getDescriptor().findServiceByName("TestService");
    }

    @Bean
    public Descriptors.FileDescriptor[] testEntityFileDescriptors() {
        return new Descriptors.FileDescriptor[]{Domain.getDescriptor()};
    }
}
