package com.webapp.config;

import com.webapp.loginmodule.AuthorityGranterImpl;
import com.webapp.loginmodule.JaasLoginModule;
import com.webapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.AbstractJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;

import javax.security.auth.login.AppConfigurationEntry;
import java.util.Map;
@Configuration
@RequiredArgsConstructor
public class JaasConfig {
    private final UserRepository userRepository;

    @Bean
    public InMemoryConfiguration configuration() {
        AppConfigurationEntry configEntry = new AppConfigurationEntry(JaasLoginModule.class.getName(),
                AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
                Map.of("userRepository", userRepository));
        var configurationEntries = new AppConfigurationEntry[] {configEntry};
        return new InMemoryConfiguration(Map.of("SPRINGSECURITY", configurationEntries));
    }

    @Bean
    public AbstractJaasAuthenticationProvider jaasAuthenticationProvider(javax.security.auth.login.Configuration configuration) {
        var provider = new DefaultJaasAuthenticationProvider();
        provider.setConfiguration(configuration);
        provider.setAuthorityGranters(new AuthorityGranter[] {new AuthorityGranterImpl(userRepository)});
        return provider;
    }
}