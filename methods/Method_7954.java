public static StaticLayout generateStaticLayout(CharSequence text,TextPaint paint,int maxWidth,int smallWidth,int linesCount,int maxLines){
  SpannableStringBuilder stringBuilder=new SpannableStringBuilder(text);
  int addedChars=0;
  StaticLayout layout=new StaticLayout(text,paint,smallWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
  for (int a=0; a < linesCount; a++) {
    Layout.Directions directions=layout.getLineDirections(a);
    if (layout.getLineLeft(a) != 0 || layout.isRtlCharAt(layout.getLineStart(a)) || layout.isRtlCharAt(layout.getLineEnd(a))) {
      maxWidth=smallWidth;
    }
    int pos=layout.getLineEnd(a);
    if (pos == text.length()) {
      break;
    }
    pos--;
    if (stringBuilder.charAt(pos + addedChars) == ' ') {
      stringBuilder.replace(pos + addedChars,pos + addedChars + 1,"\n");
    }
 else     if (stringBuilder.charAt(pos + addedChars) != '\n') {
      stringBuilder.insert(pos + addedChars,"\n");
      addedChars++;
    }
    if (a == layout.getLineCount() - 1 || a == maxLines - 1) {
      break;
    }
  }
  return StaticLayoutEx.createStaticLayout(stringBuilder,paint,maxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,AndroidUtilities.dp(1),false,TextUtils.TruncateAt.END,maxWidth,maxLines,true);
}
