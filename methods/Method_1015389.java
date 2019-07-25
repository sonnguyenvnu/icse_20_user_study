public String dump(){
  StringBuilder sb=new StringBuilder();
  for (  Map.Entry<K,Value<V>> entry : map.entrySet()) {
    sb.append(entry.getKey()).append(": ");
    V val=getValue(entry.getValue());
    if (val != null) {
      if (val instanceof byte[])       sb.append(" (" + ((byte[])val).length).append(" bytes)");
 else       sb.append(val);
    }
    sb.append("\n");
  }
  return sb.toString();
}
