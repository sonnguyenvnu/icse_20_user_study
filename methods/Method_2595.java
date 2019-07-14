public MDAGNode transition(char[] str){
  int charCount=str.length;
  MDAGNode currentNode=this;
  for (int i=0; i < charCount; ++i) {
    currentNode=currentNode.transition(str[i]);
    if (currentNode == null)     break;
  }
  return currentNode;
}
