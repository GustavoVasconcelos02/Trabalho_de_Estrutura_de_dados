package src.trie;

public class Trie {
 private final TrieNode root;
 
 public Trie ()
 {
    root = new TrieNode();
 }

 public void insert(String word, String archive)
 {
    word = word.replaceAll(".", "");
    word = word.replaceAll(",", "");
    String [] arr = word.split(" ");

    for(int i =0;i< arr.length;i++)
    {
        TrieNode node = root;
        for (char ch : arr[i].toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
        node.archive.insert(archive);
    } 
 }
 public boolean search(String word) {
   TrieNode node = root;
   for (char ch : word.toCharArray()) {
       node = node.children.get(ch);
       if (node == null) {
           return false;
       }
   }
   node.archive.print();
   return true;
}

}
