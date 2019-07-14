protected String delimitSet(Set<IOrderFlags> items){
  String delimitedSetString=null;
  if (items != null && !items.isEmpty()) {
    StringBuilder delimitStringBuilder=null;
    for (    Object item : items) {
      if (item instanceof KrakenOrderFlags) {
        if (delimitStringBuilder == null) {
          delimitStringBuilder=new StringBuilder(item.toString());
        }
 else {
          delimitStringBuilder.append(",").append(item.toString());
        }
      }
    }
    if (delimitStringBuilder != null) {
      delimitedSetString=delimitStringBuilder.toString();
    }
  }
  return delimitedSetString;
}
