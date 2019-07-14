@Override public void bind(@NonNull LithoView view){
  Component<T> component=getComponent(view.getComponentContext());
  if (view.getComponentTree() == null) {
    view.setComponentTree(ComponentTree.create(view.getComponentContext(),component).asyncStateUpdates(false).layoutThreadLooper(Looper.getMainLooper()).incrementalMount(false).layoutDiffing(false).build());
  }
  view.setComponent(component);
}
