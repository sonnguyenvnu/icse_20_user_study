private boolean search(String bulk){
  String a=textField.getText();
  String b=bulk;
  if (regex.isSelected())   return Pattern.matches(a,b);
  if (wholew.isSelected())   a=" " + a + " ";
  if (!mcase.isSelected()) {
    a=a.toLowerCase();
    b=b.toLowerCase();
  }
  if (b.contains(a))   return true;
  return false;
}
