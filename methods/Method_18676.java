@OnUnmount protected static void onUnmount(ComponentContext c,DraweeDrawable<GenericDraweeHierarchy> mountedDrawable){
  mountedDrawable.unmount();
}
