package gm.zona_fit;

import gm.zona_fit.gui.ZonaFitForma;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class ZonaFitSwing {
    public static void main(String[] args) {
        //instanciar la fabrica de spring
        ConfigurableApplicationContext contextoSpring = new SpringApplicationBuilder(ZonaFitSwing.class).headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        //crear un objeto de swing
        SwingUtilities.invokeLater(()-> {
            ZonaFitForma  zonaFitForma = contextoSpring.getBean(ZonaFitForma.class);
            zonaFitForma.setVisible(true);
        });
    }
}
