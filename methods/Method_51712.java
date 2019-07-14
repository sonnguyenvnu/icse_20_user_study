protected void fireViewerModelEvent(ViewerModelEvent e){
  for (int i=0; i < listeners.size(); i++) {
    listeners.get(i).viewerModelChanged(e);
  }
}
