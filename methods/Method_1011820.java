public void update(){
  int i_a=0;
  int i_b=0;
  try {
    i_a=Integer.parseInt(inputField_a.getText());
    i_b=Integer.parseInt(inputField_b.getText());
  }
 catch (  NumberFormatException e) {
  }
  outputField_a.setText("" + (i_a * 10 + i_b * 5));
}
