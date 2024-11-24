package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileContent {
    private final int TAMANHO_TABELA = 30;
    public ArchiveService [] getFiles(String path)
    {
        File lib = new File(path);
        File [] files = lib.listFiles();
        ArchiveService [] archives = new ArchiveService[TAMANHO_TABELA];

        for(int i = 0; i< files.length;i++)
        {
            archives[i] = getFileContent(files[i]);
        }
        return archives;

    }

    public ArchiveService getFileContent(File arquivo) {

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
        throw new RuntimeException("Erro ao ler conteudo do arquivo");
    }
    
}
