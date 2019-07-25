public static ImageShapeStyle parse(String style){
  float height=10;
  float width=10;
  String[] tokens=style.split(STYLE_DELIMITER);
  int count=0;
  for (  String token : tokens) {
    token=token.trim();
    if (token.startsWith(WIDTH_TOKEN)) {
      token=token.substring(WIDTH_TOKEN.length());
      token=token.substring(0,token.length() - 2);
      try {
        width=Float.parseFloat(token);
        count++;
      }
 catch (      NumberFormatException nfEx) {
      }
    }
 else     if (token.startsWith(HEIGHT_TOKEN)) {
      token=token.substring(HEIGHT_TOKEN.length());
      token=token.substring(0,token.length() - 2);
      try {
        height=Float.parseFloat(token);
        count++;
      }
 catch (      NumberFormatException nfEx) {
      }
    }
  }
  return new ImageShapeStyle(height,width,count == 2);
}
