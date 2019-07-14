private View findChildById(ViewGroup viewGroup,int id){
  for (int i=0, count=viewGroup.getChildCount(); i < count; ++i) {
    View child=viewGroup.getChildAt(i);
    int childId=child.getId();
    if (childId == id) {
      return child;
    }
 else     if (child instanceof ViewGroup && childId != mLoadingViewId && childId != mContentViewId && childId != mEmptyViewId && childId != mErrorViewId) {
      child=findChildById((ViewGroup)child,id);
      if (child != null) {
        return child;
      }
    }
  }
  return null;
}
