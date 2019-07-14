private void init(Context ctx,Drawable defaultDrawable){
  if (draweeHolder == null) {
    Resources resources=ctx.getResources();
    GenericDraweeHierarchy hierarchy=new GenericDraweeHierarchyBuilder(resources).setPlaceholderImage(defaultDrawable).setFailureImage(defaultDrawable).build();
    draweeHolder=DraweeHolder.create(hierarchy,ctx);
  }
}
