public String parseSftpPath(String a){
  if (a.contains("@"))   return "ssh://" + a.substring(a.lastIndexOf("@") + 1,a.length());
 else   return a;
}
