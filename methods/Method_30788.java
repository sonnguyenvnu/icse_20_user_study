private FlexibleSpaceContentView findContentView(View view){
  if (view instanceof FlexibleSpaceContentView) {
    return (FlexibleSpaceContentView)view;
  }
 else   if (view instanceof ViewGroup) {
    ViewGroup viewGroup=(ViewGroup)view;
    for (int i=0, count=viewGroup.getChildCount(); i < count; ++i) {
      FlexibleSpaceContentView contentView=findContentView(viewGroup.getChildAt(i));
      if (contentView != null) {
        return contentView;
      }
    }
  }
  return null;
}
