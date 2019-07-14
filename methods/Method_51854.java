private String stripIntValue(){
  String image=getImage().toLowerCase(Locale.ROOT).replaceAll("_","");
  boolean isNegative=false;
  if (image.charAt(0) == '-') {
    isNegative=true;
    image=image.substring(1);
  }
  if (image.endsWith("l")) {
    image=image.substring(0,image.length() - 1);
  }
  if (image.charAt(0) == '0' && image.length() > 1) {
    if (image.charAt(1) == 'x' || image.charAt(1) == 'b') {
      image=image.substring(2);
    }
 else {
      image=image.substring(1);
    }
  }
  if (isNegative) {
    return "-" + image;
  }
  return image;
}
