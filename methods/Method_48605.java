private String printPropertyKeys(boolean calledDirectly){
  StringBuilder sb=new StringBuilder();
  String pattern="%-30s | %-11s | %-50s |\n";
  Iterable<PropertyKey> keys=getRelationTypes(PropertyKey.class);
  boolean hasResults=false;
  if (calledDirectly) {
    sb.append(FIRSTDASH);
  }
 else {
    sb.append(DASHBREAK);
  }
  sb.append(String.format(pattern,"Property Key Name","Cardinality","Data Type"));
  sb.append(DASHBREAK);
  for (  PropertyKey key : keys) {
    hasResults=true;
    sb.append(String.format(pattern,key.name(),key.cardinality(),key.dataType()));
  }
  if (hasResults && calledDirectly) {
    sb.append(DASHBREAK);
  }
  return sb.toString();
}
