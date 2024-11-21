package src;

import huffman.HuffmanTrie;

public class HuffmanService {
    private HuffmanTrie huff;

    public HuffmanService (String input){
        this.huff = new HuffmanTrie(input);
    }

    public String compress (String input)
    {
        return this.huff.compress(input);
    }
    public String decompress(String input)
    {
        return this.huff.decompress(input);
    }
    
}
