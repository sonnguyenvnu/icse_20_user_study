private FlexibleSpaceHeaderView findHeaderView(View view){
  if (view instanceof FlexibleSpaceHeaderView) {
    return (FlexibleSpaceHeaderView)view;
  }
 else   if (view instanceof ViewGroup) {
    ViewGroup viewGroup=(ViewGroup)view;
    for (int i=0, count=viewGroup.getChildCount(); i < count; ++i) {
      FlexibleSpaceHeaderView headerView=findHeaderView(viewGroup.getChildAt(i));
      if (headerView != null) {
        return headerView;
      }
    }
  }
  return null;
}
