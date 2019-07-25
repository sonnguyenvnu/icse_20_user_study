public static String purge(String s){
  final String regex="(?i)\\<img\\s+src=\"(?:[^\"]+[/\\\\])?([^/\\\\\\d.]+)\\d*(\\.\\w+)\"/\\>";
  s=s.replaceAll(regex,"<img src=\"$1$2\"/>");
  final String regex2="(?i)image=\"(?:[^\"]+[/\\\\])?([^/\\\\\\d.]+)\\d*(\\.\\w+)\"";
  s=s.replaceAll(regex2,"image=\"$1$2\"");
  return s;
}
