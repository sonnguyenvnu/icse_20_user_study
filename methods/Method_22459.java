public boolean matches(Contribution contrib,String typed){
  int colon=typed.indexOf(":");
  if (colon != -1) {
    String isText=typed.substring(0,colon);
    String property=typed.substring(colon + 1);
    if (!isProperty(property)) {
      return true;
    }
    if ("is".equals(isText) || "has".equals(isText)) {
      return hasProperty(contrib,typed.substring(colon + 1));
    }
 else     if ("not".equals(isText)) {
      return !hasProperty(contrib,typed.substring(colon + 1));
    }
  }
  typed=".*" + typed.toLowerCase() + ".*";
  return (matchField(contrib.getName(),typed) || matchField(contrib.getAuthorList(),typed) || matchField(contrib.getSentence(),typed) || matchField(contrib.getParagraph(),typed) || contrib.hasCategory(typed));
}
