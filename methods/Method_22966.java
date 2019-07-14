public void setVisible(){
  int roughWidth=getWidth() + 20;
  Point p=null;
  Editor editor=base.getActiveEditor();
  if (editor == null || (p=editor.getLocation()).x < roughWidth) {
    setLocationRelativeTo(null);
  }
 else {
    setLocation(p.x - roughWidth,p.y);
  }
  setVisible(true);
}
