private void updateFontEntry(int flags,TextPaint paint,Typeface typefaceNormal,Typeface typefaceBoldItalic,Typeface typefaceBold,Typeface typefaceItalic){
  if ((flags & TEXT_FLAG_MEDIUM) != 0 && (flags & TEXT_FLAG_ITALIC) != 0) {
    paint.setTypeface(typefaceBoldItalic);
  }
 else   if ((flags & TEXT_FLAG_MEDIUM) != 0) {
    paint.setTypeface(typefaceBold);
  }
 else   if ((flags & TEXT_FLAG_ITALIC) != 0) {
    paint.setTypeface(typefaceItalic);
  }
 else   if ((flags & TEXT_FLAG_MONO) != 0) {
  }
 else {
    paint.setTypeface(typefaceNormal);
  }
}
