public static int getAppBarWidth(int width,Context context){
  if (shouldUseWideLayout(context)) {
    if (CardUtils.getColumnCount(context) == 2) {
      return width * 2 / 5;
    }
 else {
      Resources resources=context.getResources();
      int cardListHorizontalPadding=resources.getDimensionPixelOffset(R.dimen.card_list_horizontal_padding);
      int cardHorizontalMargin=resources.getDimensionPixelOffset(R.dimen.card_horizontal_margin);
      int cardShadowHorizontalMargin=resources.getDimensionPixelOffset(R.dimen.card_shadow_horizontal_margin);
      return (width - 2 * cardListHorizontalPadding) / 3 + cardListHorizontalPadding + cardHorizontalMargin - cardShadowHorizontalMargin;
    }
  }
 else {
    return width;
  }
}
