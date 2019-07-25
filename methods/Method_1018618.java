@Override public TrieNodeImpl put(String path,EntryPoint e){
  LOG.info("Entrie.put(" + path + ")");
  path=TrieNode.canonicalise(path);
  if (path.length() == 0) {
    return new TrieNodeImpl(children,Optional.of(e));
  }
  String[] elements=path.split("/");
  TrieNode existing=children.getOrDefault(elements[0],TrieNodeImpl.empty());
  TrieNode newChild=existing.put(path.substring(elements[0].length()),e);
  HashMap<String,TrieNode> newChildren=new HashMap<>(children);
  newChildren.put(elements[0],newChild);
  return new TrieNodeImpl(newChildren,value);
}
