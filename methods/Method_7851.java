private void updatePaintColors(){
  ApplicationLoader.applicationContext.getSharedPreferences("articles",Activity.MODE_PRIVATE).edit().putInt("font_color",selectedColor).commit();
  int currentColor=getSelectedColor();
  if (currentColor == 0) {
    backgroundPaint.setColor(0xffffffff);
    for (int i=0; i < listView.length; i++) {
      listView[i].setGlowColor(0xfff5f6f7);
    }
  }
 else   if (currentColor == 1) {
    backgroundPaint.setColor(0xfff5efdc);
    for (int i=0; i < listView.length; i++) {
      listView[i].setGlowColor(0xfff5efdc);
    }
  }
 else   if (currentColor == 2) {
    backgroundPaint.setColor(0xff141414);
    for (int i=0; i < listView.length; i++) {
      listView[i].setGlowColor(0xff141414);
    }
  }
  if (listTextPointerPaint != null) {
    listTextPointerPaint.setColor(getTextColor());
  }
  if (listTextNumPaint != null) {
    listTextNumPaint.setColor(getTextColor());
  }
  if (embedPostAuthorPaint != null) {
    embedPostAuthorPaint.setColor(getTextColor());
  }
  if (channelNamePaint != null) {
    if (channelBlock == null) {
      channelNamePaint.setColor(getTextColor());
    }
 else {
      channelNamePaint.setColor(0xffffffff);
    }
  }
  if (relatedArticleHeaderPaint != null) {
    relatedArticleHeaderPaint.setColor(getTextColor());
  }
  if (relatedArticleTextPaint != null) {
    relatedArticleTextPaint.setColor(getGrayTextColor());
  }
  if (embedPostDatePaint != null) {
    if (currentColor == 0) {
      embedPostDatePaint.setColor(0xff8f97a0);
    }
 else {
      embedPostDatePaint.setColor(getGrayTextColor());
    }
  }
  createPaint(true);
  setMapColors(titleTextPaints);
  setMapColors(kickerTextPaints);
  setMapColors(subtitleTextPaints);
  setMapColors(headerTextPaints);
  setMapColors(subheaderTextPaints);
  setMapColors(quoteTextPaints);
  setMapColors(preformattedTextPaints);
  setMapColors(paragraphTextPaints);
  setMapColors(listTextPaints);
  setMapColors(embedPostTextPaints);
  setMapColors(mediaCaptionTextPaints);
  setMapColors(mediaCreditTextPaints);
  setMapColors(photoCaptionTextPaints);
  setMapColors(photoCreditTextPaints);
  setMapColors(authorTextPaints);
  setMapColors(footerTextPaints);
  setMapColors(embedPostCaptionTextPaints);
  setMapColors(relatedArticleTextPaints);
  setMapColors(detailsTextPaints);
  setMapColors(tableTextPaints);
}
