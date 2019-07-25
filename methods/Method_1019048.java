@Override public void reset(){
  index.setText(String.valueOf(method.localVariables.size()));
  name.setText("");
  desc.setText("");
  signature.setText("");
  start.setLabel(null);
  end.setLabel(null);
}
