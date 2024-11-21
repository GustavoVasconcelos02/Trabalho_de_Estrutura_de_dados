package trie;

public class TrieTest {
    
	public static void main(String[] args) {
        Trie trie = new Trie();
        String texto = "Transforme este texto em um vetor de palavras";
        String texto2 = "Teste palavras";
        String texto3 = "Transforme novo teste";
        trie.insert(texto, "texto.txt");
        trie.insert(texto2, "texto2.txt");
        trie.insert(texto3, "texto3.txt");
        
        trie.search("palavras"); // true
        //System.out.println(trie.startsWith("pal")); // true
        //System.out.println(trie.search("pala"));    // false
    }
    
}
