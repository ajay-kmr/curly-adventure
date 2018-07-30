package com.example.enverexaample.bootstrap;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component(value = "coreApplicationReadyEventHandlerService")
@CommonsLog
public class ApplicationReadyEventHandlerService implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("************ Called " + ApplicationReadyEventHandlerService.class.getName() + " -> onApplicationEvent()");
    }


}
