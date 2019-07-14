public void addWord(String word){
  TrieNode node=root;
  for (  char c : word.toCharArray()) {
    if (node.children[c - 'a'] == null) {
      node.children[c - 'a']=new TrieNode();
    }
    node=node.children[c - 'a'];
  }
  node.item=word;
}
