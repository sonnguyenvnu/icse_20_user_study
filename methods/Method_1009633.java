@PostConstruct public void init(){
  setBorder(new EmptyBorder(5,5,5,5));
  setFocusable(false);
  setRootVisible(false);
  setRowHeight(36);
  Application.increaseFontSize(this);
  setToggleClickCount(1);
  addTreeSelectionListener(new TreeSelectionListener(){
    public void valueChanged(    TreeSelectionEvent e){
      DefaultMutableTreeNode node=getSelectedNode();
      if (node == null)       return;
      if (node.getUserObject() instanceof Container) {
        presenter.onContainerSelected((Container)node.getUserObject());
      }
 else       if (node.getUserObject() instanceof Item) {
        presenter.onItemSelected((Item)node.getUserObject());
      }
    }
  }
);
}
