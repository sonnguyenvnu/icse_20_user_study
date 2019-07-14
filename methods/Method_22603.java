public void showSketchbookFrame(){
  if (sketchbookFrame == null) {
    sketchbookFrame=new SketchbookFrame(base,this);
  }
  sketchbookFrame.setVisible();
}
