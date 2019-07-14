/** 
 * ?????FragmentTransaction?show?hide?????????????onHiddenChanged. ?????show?Fragment ??????? ???hide?show
 * @param hidden hidden True if the fragment is now hidden, false if it is notvisible.
 */
@Override public void onHiddenChanged(boolean hidden){
  super.onHiddenChanged(hidden);
  if (!hidden) {
    isVisible=true;
    onVisible();
  }
 else {
    isVisible=false;
    onInvisible();
  }
}
