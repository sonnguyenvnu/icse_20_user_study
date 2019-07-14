public void setLeftDrawable(int resId){
  setLeftDrawable(resId == 0 ? null : getContext().getResources().getDrawable(resId));
}
