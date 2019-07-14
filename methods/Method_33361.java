private void swapLabelsColor(Label lbl1,Label lbl2){
  Paint color=lbl1.getTextFill();
  lbl1.setTextFill(lbl2.getTextFill());
  lbl2.setTextFill(color);
}
