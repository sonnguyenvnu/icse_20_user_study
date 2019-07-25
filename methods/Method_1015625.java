public String dump(){
  StringBuilder sb=new StringBuilder("attrs\n-----\n");
  for (  Map.Entry<String,Map<String,ResourceDMBean.Accessor>> e : attrs.entrySet()) {
    sb.append(e.getKey() + ":\n");
    Map<String,ResourceDMBean.Accessor> val=e.getValue();
    val.forEach((key,value) -> sb.append(key + ": " + value).append("\n"));
  }
  sb.append("\nsetters\n--------\n");
  for (  Map.Entry<String,Map<String,ResourceDMBean.Accessor>> e : setters.entrySet()) {
    sb.append(e.getKey() + ":\n");
    Map<String,ResourceDMBean.Accessor> val=e.getValue();
    val.forEach((key,value) -> sb.append(key + ": " + value).append("\n"));
  }
  sb.append("\n");
  sb.append("\noperations\n--------\n");
  for (  Map.Entry<String,Map<String,ResourceDMBean.MethodAccessor>> e : operations.entrySet()) {
    sb.append(e.getKey() + ":\n");
    Map<String,ResourceDMBean.MethodAccessor> val=e.getValue();
    val.forEach((key,value) -> sb.append(key + ": " + value).append("\n"));
  }
  sb.append("\n");
  return sb.toString();
}
