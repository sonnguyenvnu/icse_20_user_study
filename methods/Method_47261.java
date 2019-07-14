/** 
 * Clear listener in Switch for specify ViewGroup.
 * @param viewGroup The ViewGroup that will need to clear the listener.
 */
private void clearListenerInViewGroup(ViewGroup viewGroup){
  if (null == viewGroup) {
    return;
  }
  int count=viewGroup.getChildCount();
  for (int n=0; n < count; ++n) {
    View childView=viewGroup.getChildAt(n);
    if (childView instanceof Switch) {
      final Switch switchView=(Switch)childView;
      switchView.setOnCheckedChangeListener(null);
      return;
    }
 else     if (childView instanceof ViewGroup) {
      ViewGroup childGroup=(ViewGroup)childView;
      clearListenerInViewGroup(childGroup);
    }
  }
}
