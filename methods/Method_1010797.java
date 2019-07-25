@Override public void activate(){
  ((jetbrains.mps.nodeEditor.EditorComponent)getEditorComponent()).scrollRectToVisible(GeometryUtil.getBounds(getFirstCell(),getLastCell()));
}
