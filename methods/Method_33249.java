@Override protected double computePrefWidth(double height,double topInset,double rightInset,double bottomInset,double leftInset){
  double width=100;
  String displayNodeText=displayNode.getText();
  displayNode.setText("#DDDDDD");
  width=Math.max(width,super.computePrefWidth(height,topInset,rightInset,bottomInset,leftInset));
  displayNode.setText(displayNodeText);
  return width + rightInset + leftInset;
}
