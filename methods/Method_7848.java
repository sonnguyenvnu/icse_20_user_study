private void updatePaintFonts(){
  ApplicationLoader.applicationContext.getSharedPreferences("articles",Activity.MODE_PRIVATE).edit().putInt("font_type",selectedFont).commit();
  Typeface typefaceNormal=selectedFont == 0 ? Typeface.DEFAULT : Typeface.SERIF;
  Typeface typefaceItalic=selectedFont == 0 ? AndroidUtilities.getTypeface("fonts/ritalic.ttf") : Typeface.create("serif",Typeface.ITALIC);
  Typeface typefaceBold=selectedFont == 0 ? AndroidUtilities.getTypeface("fonts/rmedium.ttf") : Typeface.create("serif",Typeface.BOLD);
  Typeface typefaceBoldItalic=selectedFont == 0 ? AndroidUtilities.getTypeface("fonts/rmediumitalic.ttf") : Typeface.create("serif",Typeface.BOLD_ITALIC);
  for (int a=0; a < quoteTextPaints.size(); a++) {
    updateFontEntry(quoteTextPaints.keyAt(a),quoteTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < preformattedTextPaints.size(); a++) {
    updateFontEntry(preformattedTextPaints.keyAt(a),preformattedTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < paragraphTextPaints.size(); a++) {
    updateFontEntry(paragraphTextPaints.keyAt(a),paragraphTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < listTextPaints.size(); a++) {
    updateFontEntry(listTextPaints.keyAt(a),listTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < embedPostTextPaints.size(); a++) {
    updateFontEntry(embedPostTextPaints.keyAt(a),embedPostTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < mediaCaptionTextPaints.size(); a++) {
    updateFontEntry(mediaCaptionTextPaints.keyAt(a),mediaCaptionTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < mediaCreditTextPaints.size(); a++) {
    updateFontEntry(mediaCreditTextPaints.keyAt(a),mediaCreditTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < photoCaptionTextPaints.size(); a++) {
    updateFontEntry(photoCaptionTextPaints.keyAt(a),photoCaptionTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < photoCreditTextPaints.size(); a++) {
    updateFontEntry(photoCreditTextPaints.keyAt(a),photoCreditTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < authorTextPaints.size(); a++) {
    updateFontEntry(authorTextPaints.keyAt(a),authorTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < footerTextPaints.size(); a++) {
    updateFontEntry(footerTextPaints.keyAt(a),footerTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < embedPostCaptionTextPaints.size(); a++) {
    updateFontEntry(embedPostCaptionTextPaints.keyAt(a),embedPostCaptionTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < relatedArticleTextPaints.size(); a++) {
    updateFontEntry(relatedArticleTextPaints.keyAt(a),relatedArticleTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < detailsTextPaints.size(); a++) {
    updateFontEntry(detailsTextPaints.keyAt(a),detailsTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
  for (int a=0; a < tableTextPaints.size(); a++) {
    updateFontEntry(tableTextPaints.keyAt(a),tableTextPaints.valueAt(a),typefaceNormal,typefaceBoldItalic,typefaceBold,typefaceItalic);
  }
}
