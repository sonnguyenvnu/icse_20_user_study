private void populate(ObjectNode node,List<String> path,int pathIndex,String value){
  String segment=path.get(pathIndex);
  if (pathIndex == path.size() - 1) {
    node.set(segment,TextNode.valueOf(value));
  }
 else {
    ObjectNode childNode=(ObjectNode)node.get(segment);
    if (childNode == null) {
      childNode=node.putObject(segment);
    }
    populate(childNode,path,pathIndex + 1,value);
  }
}
