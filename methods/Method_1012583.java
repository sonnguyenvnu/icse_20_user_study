private boolean contains(Node node,String word,int index){
  if (index == word.length())   return node.isWord;
  char c=word.charAt(index);
  if (node.next.get(c) == null)   return false;
  return contains(node.next.get(c),word,index + 1);
}
