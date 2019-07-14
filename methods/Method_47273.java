private void setEditTextIcon(@DrawableRes Integer drawable){
  @DrawableRes int drawableInt=drawable != null ? drawable : 0;
  mEditText.setCompoundDrawablesWithIntrinsicBounds(0,0,drawableInt,0);
}
