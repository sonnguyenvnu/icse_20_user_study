private String getTunnelledDomainCSV(){
  StringBuilder sb=new StringBuilder();
  for (  String domain : tunnelledMBeanDomains) {
    sb.append(domain).append(",");
  }
  return sb.deleteCharAt(sb.length() - 1).toString();
}
