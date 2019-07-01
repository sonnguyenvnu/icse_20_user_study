protected Set<Node> _XXXXX_(Set<BookieSocketAddress> excludeBookies){
  Set<Node> nodes=new HashSet<Node>();
  for (  BookieSocketAddress addr : excludeBookies) {
    BookieNode bn=knownBookies.get(addr);
    if (null == bn) {
      bn=createBookieNode(addr);
    }
    nodes.add(bn);
  }
  return nodes;
}