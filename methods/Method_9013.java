public ImageView addIconTab(Drawable drawable){
  final int position=tabCount++;
  ImageView tab=new ImageView(getContext());
  tab.setFocusable(true);
  tab.setImageDrawable(drawable);
  tab.setScaleType(ImageView.ScaleType.CENTER);
  tab.setOnClickListener(v -> delegate.onPageSelected(position));
  tabsContainer.addView(tab);
  tab.setSelected(position == currentPosition);
  return tab;
}
