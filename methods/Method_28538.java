public boolean layoutDependsOn(CoordinatorLayout parent,BottomNavigation child,View dependency){
  return AppBarLayout.class.isInstance(dependency) || Toolbar.class.isInstance(dependency);
}
