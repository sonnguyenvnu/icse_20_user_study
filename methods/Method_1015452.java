static String print(View view){
  StringBuilder sb=new StringBuilder();
  boolean first=true;
  sb.append(view.getClass().getSimpleName() + ": ").append(view.getViewId()).append(": ");
  for (  Address mbr : view.getMembers()) {
    if (first)     first=false;
 else     sb.append(", ");
    sb.append(mbr);
  }
  return sb.toString();
}
