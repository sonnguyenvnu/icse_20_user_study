String parseSftpPath(String a){
  if (a.contains("@"))   return "ssh://" + a.substring(a.indexOf("@") + 1,a.length());
 else   return a;
}
