package archive;

import src.HuffmanService;

public class Archive {
    String nome;
    String conteudo;
    private HuffmanService huff;

    public Archive(String nome, String conteudo){
        this.nome = nome;
        this.huff = new HuffmanService(conteudo);
        this.conteudo = this.huff.compress(conteudo);

    }

    public String getNome(){
        return this.nome;
    }
    public String getConteudo(){
        return this.huff.decompress(this.conteudo);
    }

    
}
