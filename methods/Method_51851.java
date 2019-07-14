public boolean isIntLiteral(){
  String image=getImage();
  if (isInt && image != null && image.length() > 0) {
    if (!image.endsWith("l") && !image.endsWith("L")) {
      return true;
    }
  }
  return false;
}
