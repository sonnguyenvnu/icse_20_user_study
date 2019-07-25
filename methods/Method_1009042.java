public static List<Node> xpath(Node node,String xpathExpression){
  NamespaceContext nsContext=new NamespacePrefixMappings();
  return xpath(node,xpathExpression,nsContext);
}
