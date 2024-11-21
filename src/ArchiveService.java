package src;

import archive.Archive;

public class ArchiveService {
    private Archive archive;

    public ArchiveService(String nome, String conteudo){
       this.archive = new Archive(nome, conteudo);

    }

    public String getNome(){
        return this.archive.getNome();
    }
    public String getConteudo(){
        return this.archive.getConteudo();
    }
    public Archive getArchive(){
        return this.archive;
    }
}
