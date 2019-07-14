public String getExt(){
  StringBuilder sb=new StringBuilder();
  for (  Usage usage : cpuList) {
    sb.append(usage.getName());
    sb.append(",");
    sb.append(usage.getUser());
    sb.append(",");
    sb.append(usage.getSys());
    sb.append(",");
    sb.append(usage.getWait());
    sb.append(";");
  }
  return sb.toString();
}
