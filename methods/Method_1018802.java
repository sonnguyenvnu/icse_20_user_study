private void init(){
  if (!isAdded())   return;
  viewPager.setCurrentItem(currentFragPos);
  viewPager.post(this::updateCurrentFragment);
}
