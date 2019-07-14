private CharSequence getFormattedDebugString(){
  String in=VoIPService.getSharedInstance().getDebugString();
  SpannableString ss=new SpannableString(in);
  int offset=0;
  do {
    int lineEnd=in.indexOf('\n',offset + 1);
    if (lineEnd == -1)     lineEnd=in.length();
    String line=in.substring(offset,lineEnd);
    if (line.contains("IN_USE")) {
      ss.setSpan(new ForegroundColorSpan(0xFF00FF00),offset,lineEnd,0);
    }
 else {
      if (line.contains(": ")) {
        ss.setSpan(new ForegroundColorSpan(0xAAFFFFFF),offset,offset + line.indexOf(':') + 1,0);
      }
    }
  }
 while ((offset=in.indexOf('\n',offset + 1)) != -1);
  return ss;
}
