String parseSmbPath(String a){
  if (a.contains("@"))   return "smb://" + a.substring(a.indexOf("@") + 1,a.length());
 else   return a;
}
