package src;

import trie.Trie;

public class TrieService {
    private Trie trie;

    public TrieService(){
        this.trie = new Trie();
    }

    public void insert(String word, String archive){
        this.trie.insert(word, archive);
    }
    public void search(String word) {
     this.trie.search(word);
    }
    
}
