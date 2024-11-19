package src.trie;

public class TrieTest {
    
	public static void main(String[] args) {
        Trie trie = new Trie();
        String texto = "Transforme este texto em um vetor de palavras";
        String texto2 = "Teste palavras";
        String texto3 = "Transforme novo teste";

        String [] vetTexto = texto.split(" ");
        String [] vetTexto2 = texto2.split(" ");
        String [] vetTexto3 = texto3.split(" ");
        for(int i =0; i< vetTexto.length; i++)
        {
            trie.insert(vetTexto[i], "texto.txt");
        }
        for(int i =0; i< vetTexto2.length; i++)
        {
            trie.insert(vetTexto2[i], "texto2.txt");
        }
        for(int i =0; i< vetTexto3.length; i++)
        {
            trie.insert(vetTexto3[i], "texto3.txt");
        }
        
        trie.search("palavras"); // true
        //System.out.println(trie.startsWith("pal")); // true
        //System.out.println(trie.search("pala"));    // false
    }
    
}
