@Override public void _commit(boolean finish){
  Set<NodeData> selectedItems=this.myCheckTree.getSelectedItems();
  Set<NodeData> modules=SetSequence.fromSet(new LinkedHashSet<NodeData>());
  for (  NodeData item : SetSequence.fromSet(selectedItems)) {
    this.fillWithParents(item,modules);
  }
  List<NodeData> toSort=ListSequence.fromListWithValues(new LinkedList<NodeData>(),modules);
  ListSequence.fromList(toSort).sort(new Comparator<NodeData>(){
    public int compare(    NodeData a,    NodeData b){
      if ((a instanceof NamespaceData) && (b instanceof ModuleData)) {
        return -1;
      }
 else       if ((a instanceof ModuleData) && (b instanceof NamespaceData)) {
        return 1;
      }
      return a.getText().compareToIgnoreCase(b.getText());
    }
  }
,true);
  this.myGenerator.setModules(toSort);
}
