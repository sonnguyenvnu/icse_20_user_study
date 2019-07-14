public String getNormalizedImage(){
  String image=getImage();
  image=normalizeHexIntegerLiteral(image);
  image=image.replace('e','E');
  if (image.indexOf('.') == -1 && image.indexOf('E') == -1) {
    image=image + ".0";
  }
  return image;
}
