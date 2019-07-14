@OnCreateMountContent protected static DraweeDrawable<GenericDraweeHierarchy> onCreateMountContent(Context c){
  GenericDraweeHierarchy draweeHierarchy=GenericDraweeHierarchyBuilder.newInstance(c.getResources()).build();
  return new DraweeDrawable<>(c,draweeHierarchy);
}
