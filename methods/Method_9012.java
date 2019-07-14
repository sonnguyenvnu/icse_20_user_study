public TextView addIconTabWithCounter(Drawable drawable){
  final int position=tabCount++;
  FrameLayout tab=new FrameLayout(getContext());
  tab.setFocusable(true);
  tabsContainer.addView(tab);
  ImageView imageView=new ImageView(getContext());
  imageView.setImageDrawable(drawable);
  imageView.setScaleType(ImageView.ScaleType.CENTER);
  tab.setOnClickListener(v -> delegate.onPageSelected(position));
  tab.addView(imageView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  tab.setSelected(position == currentPosition);
  TextView textView=new TextView(getContext());
  textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);
  textView.setTextColor(Theme.getColor(Theme.key_chat_emojiPanelBadgeText));
  textView.setGravity(Gravity.CENTER);
  textView.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(9),Theme.getColor(Theme.key_chat_emojiPanelBadgeBackground)));
  textView.setMinWidth(AndroidUtilities.dp(18));
  textView.setPadding(AndroidUtilities.dp(5),0,AndroidUtilities.dp(5),AndroidUtilities.dp(1));
  tab.addView(textView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,18,Gravity.TOP | Gravity.LEFT,26,6,0,0));
  return textView;
}
