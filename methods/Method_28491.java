@Override public boolean layoutDependsOn(final CoordinatorLayout parent,final FloatingActionButton child,final View dependency){
  if (BottomNavigation.class.isInstance(dependency)) {
    return true;
  }
 else   if (Snackbar.SnackbarLayout.class.isInstance(dependency)) {
    return true;
  }
  return super.layoutDependsOn(parent,child,dependency);
}
