@OnBoundsDefined static void onBoundsDefined(ComponentContext context,ComponentLayout layout,@Prop Binder<RecyclerView> binder){
  binder.setSize(layout.getWidth(),layout.getHeight());
}
