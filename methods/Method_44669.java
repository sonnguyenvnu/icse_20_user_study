protected String createDelimitedString(String[] items){
  StringBuilder commaDelimitedString=null;
  if (items != null) {
    for (    String item : items) {
      if (commaDelimitedString == null) {
        commaDelimitedString=new StringBuilder(item);
      }
 else {
        commaDelimitedString.append(",").append(item);
      }
    }
  }
  return (commaDelimitedString == null) ? null : commaDelimitedString.toString();
}
