@Override public void addView(@NonNull View child,int left,int top){
  if (child.getParent() instanceof ViewGroup) {
    ViewGroup parent=(ViewGroup)child.getParent();
    LayoutTransition layoutTransition=null;
    if (parent.getLayoutTransition() != null) {
      layoutTransition=parent.getLayoutTransition();
      parent.setLayoutTransition(null);
    }
    parent.removeView(child);
    if (layoutTransition != null) {
      parent.setLayoutTransition(layoutTransition);
    }
    if (child.getParent() != null) {
      ViewGroupUtils.cancelLayoutTransition(parent);
      if (child.getParent() != null && FIELD_VIEW_PARENT != null) {
        ReflectionUtils.setFieldValue(child,FIELD_VIEW_PARENT,null);
      }
    }
    if (child.getParent() != null) {
      return;
    }
  }
  child.setTag(R.id.overlay_layout_params_backup,child.getLayoutParams());
  addView(child,initParams(child,left,top));
  invalidate();
}
