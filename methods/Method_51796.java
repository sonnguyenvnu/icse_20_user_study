@Override public String getImage(){
  String result=super.getImage();
  if (result == null && hasDescendantOfType(ASTName.class)) {
    result=getFirstDescendantOfType(ASTName.class).getImage();
  }
  return result;
}
