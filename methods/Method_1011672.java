public TreeBuilder text(String text){
  myRoot.setText(text);
  myRoot.setNodeIdentifier(text == null ? "null" : text.replace(MPSTree.TREE_PATH_SEPARATOR," "));
  return this;
}
