/** 
 * parse
 * @param ssb the original content,the class type is {@link SpannableStringBuilder}
 * @return the content after parsing
 */
@NonNull private SpannableStringBuilder parse(@NonNull SpannableStringBuilder ssb){
  SpannableStringBuilder tmp=new SpannableStringBuilder();
  String tmpTotal=ssb.toString();
  while (true) {
    int position4Key0=tmpTotal.indexOf(SyntaxKey.KEY_IMAGE_LEFT);
    int position4Key1=tmpTotal.indexOf(SyntaxKey.KEY_IMAGE_MIDDLE);
    int position4Key2=tmpTotal.indexOf(SyntaxKey.KEY_IMAGE_RIGHT);
    if (position4Key0 == -1 || position4Key1 == -1 || position4Key2 == -1) {
      break;
    }
    if (position4Key0 < position4Key1 && position4Key1 < position4Key2) {
      int tmpCenter=tmpTotal.indexOf(SyntaxKey.KEY_IMAGE_MIDDLE);
      String tmpLeft=tmpTotal.substring(0,tmpCenter);
      int positionHeader=tmpLeft.lastIndexOf(SyntaxKey.KEY_IMAGE_LEFT);
      tmp.append(tmpTotal.substring(0,positionHeader));
      int index=tmp.length();
      tmpTotal=tmpTotal.substring(positionHeader + SyntaxKey.KEY_IMAGE_LEFT.length(),tmpTotal.length());
      int positionCenter=tmpTotal.indexOf(SyntaxKey.KEY_IMAGE_MIDDLE);
      ssb.delete(tmp.length(),tmp.length() + SyntaxKey.KEY_IMAGE_LEFT.length());
      tmp.append(tmpTotal.substring(0,positionCenter));
      tmpTotal=tmpTotal.substring(positionCenter + SyntaxKey.KEY_IMAGE_MIDDLE.length(),tmpTotal.length());
      int positionFooter=tmpTotal.indexOf(SyntaxKey.KEY_IMAGE_RIGHT);
      String link=tmpTotal.substring(0,positionFooter);
      if (index == tmp.length()) {
        tmp.append(DEFAULT_TEXT);
      }
      ssb.setSpan(new MDImageSpan(link,mSize[0],mSize[1],mMDImageLoader),index,tmp.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      ssb.delete(tmp.length(),tmp.length() + SyntaxKey.KEY_IMAGE_MIDDLE.length() + link.length() + SyntaxKey.KEY_IMAGE_RIGHT.length());
      tmpTotal=tmpTotal.substring(positionFooter + SyntaxKey.KEY_IMAGE_RIGHT.length(),tmpTotal.length());
    }
 else     if (position4Key0 < position4Key1 && position4Key0 < position4Key2 && position4Key2 < position4Key1) {
      tmpTotal=replaceFirstOne(tmpTotal,SyntaxKey.KEY_IMAGE_RIGHT,SyntaxKey.PLACE_HOLDER);
    }
 else     if (position4Key1 < position4Key0 && position4Key1 < position4Key2) {
      tmp.append(tmpTotal.substring(0,position4Key1 + SyntaxKey.KEY_IMAGE_MIDDLE.length()));
      tmpTotal=tmpTotal.substring(position4Key1 + SyntaxKey.KEY_IMAGE_MIDDLE.length(),tmpTotal.length());
    }
 else     if (position4Key2 < position4Key0 && position4Key2 < position4Key1) {
      tmp.append(tmpTotal.substring(0,position4Key2 + SyntaxKey.KEY_IMAGE_RIGHT.length()));
      tmpTotal=tmpTotal.substring(position4Key2 + SyntaxKey.KEY_IMAGE_RIGHT.length(),tmpTotal.length());
    }
  }
  return ssb;
}
