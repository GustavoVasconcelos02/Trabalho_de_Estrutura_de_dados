package trie;

public class Trie {
 private final TrieNode root;
 
 public Trie ()
 {
    root = new TrieNode();
 }

 public void insert(String word, String archive)
 {
    String text =  word.replaceAll("[^a-zA-Z0-9]", " ");
    String [] arr = text.split(" ");

    for(int i =0;i< arr.length;i++)
    {
        TrieNode node = root;
        for (char ch : arr[i].toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
        if (!node.archive.find(archive))
        {
            node.archive.insert(archive);
        }
    } 
 }
 public void search(String word) {
   TrieNode node = root;
   for (char ch : word.toCharArray()) {
       node = node.children.get(ch);
       if (node == null) {
        System.out.println("Registro não encontrado!");
        break;
       }
   }
   if(node!=null)
   {
    node.archive.print();
   }
}

}
