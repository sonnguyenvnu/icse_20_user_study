public void _XXXXX_(){
  Log.d(TAG,"Hiding Custom View");
  if (mCustomView == null)   return;
  mCustomView.setVisibility(View.GONE);
  ViewGroup parent=(ViewGroup)this.getParent();
  parent.removeView(mCustomView);
  mCustomView=null;
  mCustomViewCallback.onCustomViewHidden();
  this.setVisibility(View.VISIBLE);
}