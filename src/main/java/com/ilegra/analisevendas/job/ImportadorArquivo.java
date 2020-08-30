package com.ilegra.analisevendas.job;

import com.ilegra.analisevendas.input.LeitorArquivo;
import com.ilegra.analisevendas.output.EscritorArquivo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ImportadorArquivo {
    public static void importarArquivos() throws IOException {
        String dataFormatada = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
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
        escritorArquivo.escreverArquivo(leitorArquivo.getRelatorio(), diretorioLeitura);
    }

}
