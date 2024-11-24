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
        FileContent file = new FileContent();
        System.out.print("> Inserir documentos: ");
        String path = scanner.nextLine();
        conteudo = file.getFiles(path);
        System.out.println("Documentos inseridos com sucesso! ");
        System.out.print("> Qual a funcao de hashing (divisao/djb2): ");
        String funcaoEscolhida = scanner.nextLine().toLowerCase();

        if (funcaoEscolhida.equals("divisao")){
            for(int i = 0; i< conteudo.length;i++)
            {
                hashTableService.inserirComHashDivisao(conteudo[i].getArchive(), TAMANHO_TABELA);
                trie.insert(conteudo[i].getConteudo(),conteudo[i].getNome());
            }
        } else if (funcaoEscolhida.equals("djb2")){
            for(int i = 0; i< conteudo.length;i++)
            {
                hashTableService.inserirComHashDJB2(conteudo[i].getArchive());
                trie.insert(conteudo[i].getConteudo(),conteudo[i].getNome());
            }
        } else {
            System.err.println("Funcao de hash invalida!");
        }

        System.out.print("> Buscar palavra: ");
        String word = scanner.nextLine();
        System.out.println(word);
        trie.search(word);

    }
    
}
