private String normalizeHexIntegerLiteral(String image){
  if (image.startsWith("0x") || image.startsWith("0X")) {
    return String.valueOf(Integer.parseInt(image.substring(2),16));
  }
  return image;
}
