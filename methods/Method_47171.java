@Override public void onPageScrolled(int position,float positionOffset,int positionOffsetPixels){
  MainFragment mainFragment=mainActivity.getCurrentMainFragment();
  if (mainFragment != null && !mainFragment.selection) {
    @ColorInt int color=(int)evaluator.evaluate(position + positionOffset,startColor,endColor);
    colorDrawable.setColor(color);
    mainActivity.updateViews(colorDrawable);
  }
}
