private static void applySpansForTag(String cueId,StartTag startTag,SpannableStringBuilder text,List<WebvttCssStyle> styles,List<StyleMatch> scratchStyleMatches){
  int start=startTag.position;
  int end=text.length();
switch (startTag.name) {
case TAG_BOLD:
    text.setSpan(new StyleSpan(STYLE_BOLD),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  break;
case TAG_ITALIC:
text.setSpan(new StyleSpan(STYLE_ITALIC),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
break;
case TAG_UNDERLINE:
text.setSpan(new UnderlineSpan(),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
break;
case TAG_CLASS:
case TAG_LANG:
case TAG_VOICE:
case "":
break;
default :
return;
}
scratchStyleMatches.clear();
getApplicableStyles(styles,cueId,startTag,scratchStyleMatches);
int styleMatchesCount=scratchStyleMatches.size();
for (int i=0; i < styleMatchesCount; i++) {
applyStyleToText(text,scratchStyleMatches.get(i).style,start,end);
}
}
