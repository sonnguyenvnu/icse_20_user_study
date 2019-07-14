public void setChildren(Set<TreeNode<V>> children){
  this.children=children;
  children.forEach(node -> node.setLevel(getLevel() + 1));
}
