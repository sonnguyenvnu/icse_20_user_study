public static CharSequence generateSearchName(String name,String name2,String q){
  if (name == null && name2 == null) {
    return "";
  }
  SpannableStringBuilder builder=new SpannableStringBuilder();
  String wholeString=name;
  if (wholeString == null || wholeString.length() == 0) {
    wholeString=name2;
  }
 else   if (name2 != null && name2.length() != 0) {
    wholeString+=" " + name2;
  }
  wholeString=wholeString.trim();
  String lower=" " + wholeString.toLowerCase();
  int index;
  int lastIndex=0;
  while ((index=lower.indexOf(" " + q,lastIndex)) != -1) {
    int idx=index - (index == 0 ? 0 : 1);
    int end=q.length() + (index == 0 ? 0 : 1) + idx;
    if (lastIndex != 0 && lastIndex != idx + 1) {
      builder.append(wholeString.substring(lastIndex,idx));
    }
 else     if (lastIndex == 0 && idx != 0) {
      builder.append(wholeString.substring(0,idx));
    }
    String query=wholeString.substring(idx,Math.min(wholeString.length(),end));
    if (query.startsWith(" ")) {
      builder.append(" ");
    }
    query=query.trim();
    int start=builder.length();
    builder.append(query);
    builder.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4)),start,start + query.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    lastIndex=end;
  }
  if (lastIndex != -1 && lastIndex < wholeString.length()) {
    builder.append(wholeString.substring(lastIndex));
  }
  return builder;
}
