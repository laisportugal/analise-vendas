package com.ilegra.analisevendas.input;

import com.ilegra.analisevendas.output.EscritorArquivo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ImportadorArquivo {
    @Scheduled
    public void importarArquivos() throws IOException {
        String dataFormatada = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yy 00:00:00"));
        LeitorArquivo leitorArquivo = new LeitorArquivo();
        EscritorArquivo escritorArquivo = new EscritorArquivo();

        String diretorioLeitura = "D:\\arquivos\\in";
        try {
            leitorArquivo.lerArquivo(diretorioLeitura);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String diretorioSaida = "D:\\arquivos\\out\\" + dataFormatada + "-"
                + leitorArquivo.getNomeArquivoEntrada();
        escritorArquivo.escreverArquivo(leitorArquivo.getRelatorio(), diretorioSaida);
    }

}
