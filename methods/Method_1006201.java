@Override public String format(String fieldText){
  StringBuilder sb=new StringBuilder(100);
  if (!fieldText.contains(" and ")) {
    singleAuthor(sb,fieldText,1);
  }
 else {
    String[] names=fieldText.split(" and ");
    for (int i=0; i < names.length; i++) {
      singleAuthor(sb,names[i],i + 1);
      if (i < (names.length - 1)) {
        sb.append('\n');
      }
    }
  }
  return sb.toString();
}
