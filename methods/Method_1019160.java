/** 
 * parse
 * @param text the original content,the class type is {@link String}
 * @param ssb  the original content,the class type is {@link SpannableStringBuilder}
 * @return the content after parsing
 */
@NonNull private SpannableStringBuilder parse(@NonNull String text,@NonNull SpannableStringBuilder ssb){
  SpannableStringBuilder tmp=new SpannableStringBuilder();
  String tmpTotal=text;
  while (true) {
    int positionHeader=findPosition(tmpTotal,ssb,tmp);
    if (positionHeader == -1) {
      tmp.append(tmpTotal.substring(0,tmpTotal.length()));
      break;
    }
    tmp.append(tmpTotal.substring(0,positionHeader));
    int index=tmp.length();
    tmpTotal=tmpTotal.substring(positionHeader + SyntaxKey.KEY_CODE.length(),tmpTotal.length());
    int positionFooter=findPosition(tmpTotal,ssb,tmp);
    if (positionFooter != -1) {
      ssb.delete(tmp.length(),tmp.length() + SyntaxKey.KEY_CODE.length());
      tmp.append(tmpTotal.substring(0,positionFooter));
      ssb.setSpan(new MDCodeSpan(mColor),index,tmp.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      ssb.setSpan(new TypefaceSpan("monospace"),index,tmp.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      ssb.delete(tmp.length(),tmp.length() + SyntaxKey.KEY_CODE.length());
    }
 else {
      tmp.append(SyntaxKey.KEY_CODE);
      tmp.append(tmpTotal.substring(0,tmpTotal.length()));
      break;
    }
    tmpTotal=tmpTotal.substring(positionFooter + SyntaxKey.KEY_CODE.length(),tmpTotal.length());
  }
  return ssb;
}
