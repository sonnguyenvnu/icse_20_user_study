private void preorder(TreeNode node,int level,int pos){
  if (node != null) {
    preorder(node.left,level + 1,pos * 2);
    map.putIfAbsent(level,new ArrayList<>());
    map.get(level).add(pos);
    preorder(node.right,level + 1,pos * 2 + 1);
  }
}
