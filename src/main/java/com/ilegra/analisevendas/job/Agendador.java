package com.ilegra.analisevendas.job;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@Configuration
@EnableScheduling
public class Agendador {
    @Scheduled (fixedDelay = 1000 )
    public void agendarLeituraArquivos() throws IOException {
        System.out.println("Fixed delay task - " +System.currentTimeMillis() / 1000);
        ImportadorArquivo importadorArquivo = new ImportadorArquivo();
        importadorArquivo.importarArquivos();
    }
}
