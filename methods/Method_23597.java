public String join(String separator){
  if (count == 0) {
    return "";
  }
  StringBuilder sb=new StringBuilder();
  sb.append(data[0]);
  for (int i=1; i < count; i++) {
    sb.append(separator);
    sb.append(data[i]);
  }
  return sb.toString();
}
