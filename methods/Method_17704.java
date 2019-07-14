/** 
 * If the mount content is just a single  {@link android.view.View}- returns that view, otherwise returns null. Used by  {@link RenderThreadTransition} as for now we can only run RT animationson View
 */
@Nullable View getSingleTargetView(){
  View view=null;
  for (int i=0, size=mMountContentGroup.size(); i < size; i++) {
    final Object mountContent=resolveReference(mMountContentGroup.getAt(i));
    if (mountContent == null) {
      continue;
    }
    if (view != null || !(mountContent instanceof View)) {
      return null;
    }
    view=(View)mountContent;
  }
  return view;
}
