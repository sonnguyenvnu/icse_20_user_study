private static void addUsernamesAndHashtags(boolean isOut,CharSequence charSequence,boolean botCommands,int hashtagsType){
  try {
    Matcher matcher;
    if (hashtagsType == 1) {
      if (instagramUrlPattern == null) {
        instagramUrlPattern=Pattern.compile("(^|\\s|\\()@[a-zA-Z\\d_.]{1,32}|(^|\\s|\\()#[\\w.]+");
      }
      matcher=instagramUrlPattern.matcher(charSequence);
    }
 else {
      if (urlPattern == null) {
        urlPattern=Pattern.compile("(^|\\s)/[a-zA-Z@\\d_]{1,255}|(^|\\s|\\()@[a-zA-Z\\d_]{1,32}|(^|\\s|\\()#[\\w.]+|(^|\\s)\\$[A-Z]{3,8}([ ,.]|$)");
      }
      matcher=urlPattern.matcher(charSequence);
    }
    while (matcher.find()) {
      int start=matcher.start();
      int end=matcher.end();
      char ch=charSequence.charAt(start);
      if (hashtagsType != 0) {
        if (ch != '@' && ch != '#') {
          start++;
        }
        ch=charSequence.charAt(start);
        if (ch != '@' && ch != '#') {
          continue;
        }
      }
 else {
        if (ch != '@' && ch != '#' && ch != '/' && ch != '$') {
          start++;
        }
      }
      URLSpanNoUnderline url=null;
      if (hashtagsType == 1) {
        if (ch == '@') {
          url=new URLSpanNoUnderline("https://instagram.com/" + charSequence.subSequence(start + 1,end).toString());
        }
 else         if (ch == '#') {
          url=new URLSpanNoUnderline("https://www.instagram.com/explore/tags/" + charSequence.subSequence(start + 1,end).toString());
        }
      }
 else       if (hashtagsType == 2) {
        if (ch == '@') {
          url=new URLSpanNoUnderline("https://twitter.com/" + charSequence.subSequence(start + 1,end).toString());
        }
 else         if (ch == '#') {
          url=new URLSpanNoUnderline("https://twitter.com/hashtag/" + charSequence.subSequence(start + 1,end).toString());
        }
      }
 else {
        if (charSequence.charAt(start) == '/') {
          if (botCommands) {
            url=new URLSpanBotCommand(charSequence.subSequence(start,end).toString(),isOut ? 1 : 0);
          }
        }
 else {
          url=new URLSpanNoUnderline(charSequence.subSequence(start,end).toString());
        }
      }
      if (url != null) {
        ((Spannable)charSequence).setSpan(url,start,end,0);
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
