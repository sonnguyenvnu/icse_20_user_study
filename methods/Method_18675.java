@OnUnbind protected static void onUnbind(ComponentContext c,DraweeDrawable<GenericDraweeHierarchy> mountedDrawable,@Prop DraweeController controller){
  mountedDrawable.setController(null);
  if (controller != null) {
    controller.onViewportVisibilityHint(false);
  }
}
