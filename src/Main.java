package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;

public class Main {
    private static final int TAMANHO_TABELA = 30;

    public static void main(String[] args) {
        HashTableService hashTableService = new HashTableService(TAMANHO_TABELA); //numero de arquivos recebidos
        Scanner scanner = new Scanner(System.in);
        ArchiveService [] conteudo = new ArchiveService[TAMANHO_TABELA];
        TrieService trie = new TrieService();

        System.out.print("> Inserir documentos: ");
        String path = scanner.nextLine();
        conteudo = getArquivos(path);
        System.out.println("Documentos inseridos com sucesso! ");
        System.out.print("> Qual a funcao de hashing (divisao/djb2): ");
        String funcaoEscolhida = scanner.nextLine().toLowerCase();
        for(int i = 0; i< conteudo.length;i++)
        {
            trie.insert(conteudo[i].getConteudo(),conteudo[i].getNome());
        }
        

        if (funcaoEscolhida.equals("divisao")){
            for(int i = 0; i< conteudo.length;i++)
            {
                hashTableService.inserirComHashDivisao(conteudo[i].getArchive(), TAMANHO_TABELA);
            }
        } else if (funcaoEscolhida.equals("djb2")){
            for(int i = 0; i< conteudo.length;i++)
            {
                hashTableService.inserirComHashDJB2(conteudo[i].getArchive());
            }
        } else {
            System.err.println("Funcao de hash invalida!");
        }

        System.out.print("> Buscar palavra: ");
        String word = scanner.nextLine();
        System.out.println(word);
        trie.search(word);



    }
    private static ArchiveService [] getArquivos(String path)
    {
        File lib = new File(path);
        File [] files = lib.listFiles();
        ArchiveService [] archives = new ArchiveService[TAMANHO_TABELA];

        for(int i = 0; i< files.length;i++)
        {
            archives[i] = getConteudoDoArquivo(files[i]);
        }
        return archives;

    }

    private static ArchiveService getConteudoDoArquivo(File arquivo) {

        if (arquivo.exists() && arquivo.isFile()) {

            // Lendo o conteúdo do arquivo com BufferedReader
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                StringBuilder conteudo = new StringBuilder();
                String linha;
                while ((linha = br.readLine()) != null) {
                    conteudo.append(linha).append("\n");
                }
                return new ArchiveService(arquivo.getName(),conteudo.toString());
            } catch (Exception e) {
                System.err.println(e.getMessage());

            }
        }

        throw new RuntimeException("Erro ao ler conteudo do arquivo");
    }
}
