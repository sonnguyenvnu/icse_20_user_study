private static String makeStars(int count){
  StringBuilder builder=new StringBuilder();
  boolean first=true;
  for (int i=0; i < count; ++i) {
    if (first) {
      first=false;
    }
 else {
      builder.append(' ');
    }
    builder.append('?');
  }
  return builder.toString();
}
