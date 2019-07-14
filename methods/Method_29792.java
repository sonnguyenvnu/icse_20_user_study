/** 
 * Should be called in  {@link Fragment#onDestroy()}.
 */
public void detach(){
  Fragment fragment=mTargetFragment;
  while (fragment != null) {
    if (fragment.isRemoving()) {
      FragmentUtils.remove(this);
      break;
    }
    fragment=fragment.getParentFragment();
  }
}
