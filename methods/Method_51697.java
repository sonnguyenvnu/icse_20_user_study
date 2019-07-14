/** 
 * @see ViewerModelListener#viewerModelChanged(ViewerModelEvent)
 */
@Override public void viewerModelChanged(ViewerModelEvent e){
switch (e.getReason()) {
case ViewerModelEvent.PATH_EXPRESSION_APPENDED:
    if (e.getSource() != this) {
      xPathArea.append((String)e.getParameter());
    }
  setSelectedIndex(0);
break;
case ViewerModelEvent.CODE_RECOMPILED:
setSelectedIndex(0);
break;
default :
break;
}
}
