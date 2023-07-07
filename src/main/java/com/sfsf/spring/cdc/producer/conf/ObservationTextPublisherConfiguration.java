package com.sfsf.spring.cdc.producer.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationTextPublisher;
import io.micrometer.observation.aop.ObservedAspect;

@Configuration
public class ObservationTextPublisherConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ObservationTextPublisherConfiguration.class);

    @Bean
    ObservationHandler<Observation.Context> observationTextPublisher() {
        return new ObservationTextPublisher(log::info);
    }
    
    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }
    
}