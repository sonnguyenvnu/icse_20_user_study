public boolean presentFragment(final BaseFragment fragment,final boolean removeLast,boolean forceWithoutAnimation){
  return actionBarLayout.presentFragment(fragment,removeLast,forceWithoutAnimation,true,false);
}
