@OnBind protected static void onBind(ComponentContext c,DraweeDrawable<GenericDraweeHierarchy> mountedDrawable,@Prop DraweeController controller){
  mountedDrawable.setController(controller);
  if (controller != null) {
    controller.onViewportVisibilityHint(true);
  }
}
