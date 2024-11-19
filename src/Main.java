package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;

public class Main {
    private static final int TAMANHO_TABELA = 30;

    public static void main(String[] args) {
        var hashTableService = new HashTableService(TAMANHO_TABELA); //numero de arquivos recebidos
        var scanner = new Scanner(System.in);

        System.out.print("Inserir documentos: ");
        String path = scanner.nextLine();
        String conteudo = getConteudoDoArquivo(path);
        System.out.println("Documentos inseridos com sucesso! ");
        System.out.print("Qual a funcao de hashing (divisao/djb2): ");
        String funcaoEscolhida = scanner.nextLine().toLowerCase();

        if (funcaoEscolhida.equals("divisao")){
            hashTableService.inserirComHashDivisao(conteudo, TAMANHO_TABELA);
        } else if (funcaoEscolhida.equals("djb2")){
            hashTableService.inserirComHashDJB2(conteudo);
        } else {
            System.err.println("Funcao de hash invalida!");
        }

    }

    private static String getConteudoDoArquivo(String path) {
        File arquivo = new File(path);


        if (arquivo.exists() && arquivo.isFile()) {

            // Lendo o conteúdo do arquivo com BufferedReader
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                StringBuilder conteudo = new StringBuilder();
                String linha;
                while ((linha = br.readLine()) != null) {
                    conteudo.append(linha).append("\n");
                }
                return conteudo.toString();
            } catch (Exception e) {
                System.err.println(e.getMessage());

            }
        }

        throw new RuntimeException("Erro ao ler conteudo do arquivo");
    }
}
