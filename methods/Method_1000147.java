public LinkedHashMap<byte[],Node> prove(byte[] key){
  Node node=root;
  List<Node> nodeList=new ArrayList<>();
  if (node == null) {
    return null;
  }
  TrieKey trieKey=TrieKey.fromNormal(key);
  while (node != null) {
    Node n=node;
    if (n.getType() == NodeType.BranchNode) {
      if (trieKey.isEmpty()) {
        nodeList.add(n);
        break;
      }
      node=(Node)n.branchNodeGetChild(trieKey.getHex(0));
      if (node == null) {
        return null;
      }
      trieKey=trieKey.shift(1);
      nodeList.add(n);
    }
 else     if (n.getType() == NodeType.KVNodeNode) {
      TrieKey currentNodeKey=n.kvNodeGetKey();
      TrieKey commonPrefix=trieKey.getCommonPrefix(currentNodeKey);
      if (commonPrefix.getLength() != currentNodeKey.getLength()) {
        return null;
      }
      node=n.kvNodeGetChildNode();
      if (node == null) {
        return null;
      }
      trieKey=trieKey.shift(commonPrefix.getLength());
      nodeList.add(n);
    }
 else {
      if (!n.kvNodeGetKey().equals(trieKey)) {
        return null;
      }
      nodeList.add(n);
      break;
    }
  }
  LinkedHashMap<byte[],Node> nodeMap=new LinkedHashMap<>();
  int i=0;
  for (  Node n : nodeList) {
    List<Node> cpList=new ArrayList<>();
    nodeMap.put(childrenHash(n,cpList,0,i == 0 ? true : false),cpList.get(0));
    ++i;
  }
  return nodeMap;
}
