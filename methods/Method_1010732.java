public void activate(Window owner,Point location,Dimension size,boolean show){
  if (!myEditorActivated) {
    myEditorActivated=true;
    myEditorWindow=new EditorWindow(owner);
    myEditorWindow.setLocation(location);
    myEditorWindow.setMinimalSize(size);
    myEditorWindow.myTextLine.setText(myCachedText);
    myEditorWindow.myTextLine.setCaretPosition(myCachedCaretPosition);
    if (show) {
      myEditorWindow.relayout();
      myEditorWindow.setVisible(true);
    }
  }
}
