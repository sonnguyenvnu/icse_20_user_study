public void init(ControlPoint controlPoint,Service service){
  rootContainer=createRootContainer(service);
  rootNode=new DefaultMutableTreeNode(rootContainer){
    @Override public boolean isLeaf(){
      return false;
    }
  }
;
  DefaultTreeModel treeModel=new DefaultTreeModel(rootNode);
  setModel(treeModel);
  getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
  addTreeWillExpandListener(createContainerTreeExpandListener(controlPoint,service,treeModel));
  setCellRenderer(createContainerTreeCellRenderer());
  controlPoint.execute(createContentBrowseActionCallback(service,treeModel,getRootNode()));
}
