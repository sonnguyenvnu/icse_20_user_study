private void createMenuButton(){
  mMenuButton=new FloatingActionButton(getContext());
  mMenuButton.mShowShadow=mMenuShowShadow;
  if (mMenuShowShadow) {
    mMenuButton.mShadowRadius=Util.dpToPx(getContext(),mMenuShadowRadius);
    mMenuButton.mShadowXOffset=Util.dpToPx(getContext(),mMenuShadowXOffset);
    mMenuButton.mShadowYOffset=Util.dpToPx(getContext(),mMenuShadowYOffset);
  }
  mMenuButton.setColors(mMenuColorNormal,mMenuColorPressed,mMenuColorRipple);
  mMenuButton.mShadowColor=mMenuShadowColor;
  mMenuButton.mFabSize=mMenuFabSize;
  mMenuButton.updateBackground();
  mMenuButton.setLabelText(mMenuLabelText);
  mImageToggle=new ImageView(getContext());
  mImageToggle.setImageDrawable(mIcon);
  addView(mMenuButton,super.generateDefaultLayoutParams());
  addView(mImageToggle);
  createDefaultIconAnimation();
}
