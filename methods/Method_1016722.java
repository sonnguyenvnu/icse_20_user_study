private void populate(TreeItem<String> rootItem,String rootName,JsonNode root){
  if (rootName.equals("root")) {
    visualizer.setRoot(rootItem);
  }
  rootItem.setValue(rootName);
  JsonNode currentNode;
  List<TreeItem<String>> items=new ArrayList<>();
  if (root.isArray()) {
    Iterator<JsonNode> iterator=root.elements();
    int i=0;
    while (iterator.hasNext()) {
      currentNode=iterator.next();
      if (currentNode.isValueNode()) {
        items.add(getTreeNode(i++ + ": " + EverestUtilities.trimString(currentNode.toString())));
      }
 else       if (currentNode.isObject()) {
        TreeItem<String> newRoot=getTreeNode(null);
        newRoot.setExpanded(true);
        items.add(newRoot);
        populate(newRoot,i++ + ": [Anonymous Object]",currentNode);
      }
    }
  }
 else {
    Iterator<Map.Entry<String,JsonNode>> iterator=root.fields();
    Map.Entry<String,JsonNode> currentEntry;
    while (iterator.hasNext()) {
      currentEntry=iterator.next();
      currentNode=currentEntry.getValue();
      if (currentNode.isValueNode()) {
        items.add(getTreeNode(currentEntry.getKey() + ": " + EverestUtilities.trimString(currentNode.toString())));
      }
 else       if (currentNode.isArray() || currentNode.isObject()) {
        TreeItem<String> newRoot=getTreeNode(null);
        newRoot.setExpanded(true);
        items.add(newRoot);
        populate(newRoot,currentEntry.getKey(),currentNode);
      }
    }
  }
  rootItem.getChildren().addAll(items);
}
