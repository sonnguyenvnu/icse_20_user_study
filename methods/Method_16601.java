public static <V extends TreeSupportEntity<String>>Set<TreeNode<String>> transformationTreeNode(V parent,List<V> data){
  Set<TreeNode<String>> treeNodes=new HashSet<>();
  data.forEach(node -> {
    TreeNode<String> treeNode=new TreeNode<>();
    if (parent != null) {
      TreeNode<String> parentNode=new TreeNode<>();
      parentNode.setValue(parent.getId());
      parentNode.setChildren(treeNodes);
    }
    treeNode.setValue(node.getId());
    if (node.getChildren() != null && !node.getChildren().isEmpty()) {
      treeNode.setChildren(transformationTreeNode(node,node.getChildren()));
    }
    treeNodes.add(treeNode);
  }
);
  return treeNodes;
}
