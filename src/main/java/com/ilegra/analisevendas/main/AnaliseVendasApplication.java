package com.ilegra.analisevendas.main;

import com.ilegra.analisevendas.input.LeitorArquivo;
import com.ilegra.analisevendas.output.EscritorArquivo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class AnaliseVendasApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AnaliseVendasApplication.class, args);

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
		escritorArquivo.escreverArquivo(leitorArquivo.getRelatorio(), diretorioSaida);
	}
}
