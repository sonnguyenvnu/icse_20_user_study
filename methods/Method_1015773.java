public static <T>String print(Map<T,T> map){
  StringBuilder sb=new StringBuilder();
  boolean first=true;
  for (  Map.Entry<T,T> entry : map.entrySet()) {
    if (first)     first=false;
 else     sb.append(", ");
    sb.append(entry.getKey()).append("=").append(entry.getValue());
  }
  return sb.toString();
}
