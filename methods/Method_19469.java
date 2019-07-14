@OnUnmount static void onUnmount(ComponentContext c,TransparencyEnabledCardClipDrawable cardClipDrawable){
  cardClipDrawable.setCornerRadius(0);
  cardClipDrawable.setBackgroundColor(Color.WHITE);
}
