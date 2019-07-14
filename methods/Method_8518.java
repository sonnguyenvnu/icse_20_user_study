public void setTitleIcons(Drawable leftIcon,Drawable rightIcon){
  titleTextView.setLeftDrawable(leftIcon);
  if (!(titleTextView.getRightDrawable() instanceof ScamDrawable)) {
    titleTextView.setRightDrawable(rightIcon);
  }
}
