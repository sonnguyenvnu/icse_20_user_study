/** 
 * @see ViewerModelListener#viewerModelChanged(ViewerModelEvent)
 */
@Override public void viewerModelChanged(ViewerModelEvent e){
switch (e.getReason()) {
case ViewerModelEvent.CODE_RECOMPILED:
    tree.setModel(new ASTModel(model.getRootNode()));
  break;
case ViewerModelEvent.NODE_SELECTED:
if (e.getSource() != this) {
  List<Node> list=new ArrayList<>();
  for (Node n=(Node)e.getParameter(); n != null; n=n.jjtGetParent()) {
    list.add(n);
  }
  Collections.reverse(list);
  TreePath path=new TreePath(list.toArray());
  tree.setSelectionPath(path);
  tree.scrollPathToVisible(path);
}
break;
default :
break;
}
}
