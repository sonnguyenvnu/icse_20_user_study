public void showExamplesFrame(){
  if (examplesFrame == null) {
    examplesFrame=new ExamplesFrame(base,this);
  }
  examplesFrame.setVisible();
}
