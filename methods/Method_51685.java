private void init(){
  model.addViewerModelListener(this);
  setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),NLS.nls("AST.PANEL.TITLE")));
  setLayout(new BorderLayout());
  tree=new JTree((TreeNode)null);
  tree.addTreeSelectionListener(this);
  tree.addMouseListener(new MouseAdapter(){
    @Override public void mouseReleased(    MouseEvent e){
      if (e.isPopupTrigger()) {
        TreePath path=tree.getClosestPathForLocation(e.getX(),e.getY());
        tree.setSelectionPath(path);
        JPopupMenu menu=new ASTNodePopupMenu(model,(Node)path.getLastPathComponent());
        menu.show(tree,e.getX(),e.getY());
      }
    }
  }
);
  add(new JScrollPane(tree),BorderLayout.CENTER);
}
