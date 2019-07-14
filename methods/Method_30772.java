public boolean addViewInLayout(View child,int index,ViewGroup.LayoutParams params,boolean preventRequestLayout){
  if (mInsets != null) {
    dispatchInsetsToChild(child,params);
  }
  return mDelegate.superAddViewInLayout(child,index,params,preventRequestLayout);
}
