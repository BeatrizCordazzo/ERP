package com.moderni.erp;

import com.moderni.erp.ui.LoginFrame;
import java.awt.EventQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ErpApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ErpApplication.class);
        application.setHeadless(false);
        application.setWebApplicationType(WebApplicationType.NONE);

        ConfigurableApplicationContext context = application.run(args);
        EventQueue.invokeLater(() -> context.getBean(LoginFrame.class).setVisible(true));
    }

}
