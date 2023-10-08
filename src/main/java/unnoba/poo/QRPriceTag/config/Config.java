package unnoba.poo.QRPriceTag.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import unnoba.poo.QRPriceTag.repository.UserRepository;

@Configuration
public class Config {
    @Bean
    public UserRepository getUserDAO() {
        return new UserRepository();
    }
}
