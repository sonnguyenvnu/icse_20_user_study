public static SpannableStringBuilder replaceTags(String str,int flag,Object... args){
  try {
    int start;
    int end;
    StringBuilder stringBuilder=new StringBuilder(str);
    if ((flag & FLAG_TAG_BR) != 0) {
      while ((start=stringBuilder.indexOf("<br>")) != -1) {
        stringBuilder.replace(start,start + 4,"\n");
      }
      while ((start=stringBuilder.indexOf("<br/>")) != -1) {
        stringBuilder.replace(start,start + 5,"\n");
      }
    }
    ArrayList<Integer> bolds=new ArrayList<>();
    if ((flag & FLAG_TAG_BOLD) != 0) {
      while ((start=stringBuilder.indexOf("<b>")) != -1) {
        stringBuilder.replace(start,start + 3,"");
        end=stringBuilder.indexOf("</b>");
        if (end == -1) {
          end=stringBuilder.indexOf("<b>");
        }
        stringBuilder.replace(end,end + 4,"");
        bolds.add(start);
        bolds.add(end);
      }
      while ((start=stringBuilder.indexOf("**")) != -1) {
        stringBuilder.replace(start,start + 2,"");
        end=stringBuilder.indexOf("**");
        if (end >= 0) {
          stringBuilder.replace(end,end + 2,"");
          bolds.add(start);
          bolds.add(end);
        }
      }
    }
    if ((flag & FLAG_TAG_URL) != 0) {
      while ((start=stringBuilder.indexOf("**")) != -1) {
        stringBuilder.replace(start,start + 2,"");
        end=stringBuilder.indexOf("**");
        if (end >= 0) {
          stringBuilder.replace(end,end + 2,"");
          bolds.add(start);
          bolds.add(end);
        }
      }
    }
    SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder(stringBuilder);
    for (int a=0; a < bolds.size() / 2; a++) {
      spannableStringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf")),bolds.get(a * 2),bolds.get(a * 2 + 1),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    return spannableStringBuilder;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return new SpannableStringBuilder(str);
}
