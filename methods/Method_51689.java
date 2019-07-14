/** 
 * @see ViewerModelListener#viewerModelChanged(ViewerModelEvent)
 */
@Override public void viewerModelChanged(ViewerModelEvent e){
  evalBtn.setEnabled(model.hasCompiledTree());
}
