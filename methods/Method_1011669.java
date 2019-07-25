@Override protected MPSTreeNode rebuild(){
  if (myCurrentEditor == null || myCurrentEditor.getRootCell() == null) {
    TextTreeNode rv=new TextTreeNode("No editor selected");
    rv.setIcon(CellExplorer.CellExplorer);
    return rv;
  }
 else {
    return new CellNode(myCurrentEditor.getRootCell());
  }
}
