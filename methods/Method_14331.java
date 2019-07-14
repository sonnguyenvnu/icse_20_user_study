static public Serializable parseCellValue(String text){
  if (text.length() > 0) {
    String text2=text.trim();
    if (text2.length() > 0) {
      try {
        return Long.parseLong(text2);
      }
 catch (      NumberFormatException e) {
      }
      try {
        double d=Double.parseDouble(text2);
        if (!Double.isInfinite(d) && !Double.isNaN(d)) {
          return d;
        }
      }
 catch (      NumberFormatException e) {
      }
    }
  }
  return text;
}
