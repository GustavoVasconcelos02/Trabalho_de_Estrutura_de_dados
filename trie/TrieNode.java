package trie;

import java.util.HashMap;
import java.util.Map;

import archive.LinkedList;

public class TrieNode {
	
    Map<Character, TrieNode> children;
    LinkedList<String> archive;
    boolean isEndOfWord;

    // Construtor padrão
    public TrieNode() {
        this.children = new HashMap<>();
        this.archive = new LinkedList<>();
        this.isEndOfWord = false;
    }

    // Construtor para definir explicitamente o estado de `isEndOfWord`
    public TrieNode(boolean isEndOfWord) {
        this.children = new HashMap<>();
        this.archive= new LinkedList<>();
        this.isEndOfWord = isEndOfWord;
    }
	
}