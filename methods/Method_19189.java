@OnUnmount static void onUnmount(ComponentContext c,CardClipDrawable cardClipDrawable){
  cardClipDrawable.setCornerRadius(0);
  cardClipDrawable.setClippingColor(Color.WHITE);
  cardClipDrawable.setDisableClip(NONE);
}
