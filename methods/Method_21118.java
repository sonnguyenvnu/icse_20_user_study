private void showMenuIconWithIndicator(final boolean withIndicator){
  if (withIndicator) {
    this.menuImageButton.setImageResource(R.drawable.ic_menu_indicator);
    final Animatable menuDrawable=(Animatable)this.menuImageButton.getDrawable();
    menuDrawable.start();
  }
 else {
    this.menuImageButton.setImageResource(R.drawable.ic_menu);
  }
}
