@Override public String getSqlWithValues(){
  final StringBuilder sb=new StringBuilder();
  for (  Map.Entry<String,Value> entry : resultMap.entrySet()) {
    if (sb.length() > 0) {
      sb.append(", ");
    }
    sb.append(entry.getKey());
    sb.append(" = ");
    sb.append(entry.getValue() != null ? entry.getValue().toString() : new Value().toString());
  }
  return sb.toString();
}
