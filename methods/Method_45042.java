public DefaultMutableTreeNode loadNodesByNames(DefaultMutableTreeNode node,List<String> originalNames){
  List<TreeNodeUserObject> args=new ArrayList<>();
  for (  String originalName : originalNames) {
    args.add(new TreeNodeUserObject(originalName));
  }
  return loadNodesByUserObj(node,args);
}
