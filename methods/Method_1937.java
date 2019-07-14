@Override public VolleyDraweeHolder onCreateViewHolder(ViewGroup parent,int viewType){
  GenericDraweeHierarchy gdh=new GenericDraweeHierarchyBuilder(getContext().getResources()).setPlaceholderImage(Drawables.sPlaceholderDrawable).setFailureImage(Drawables.sErrorDrawable).build();
  InstrumentedDraweeView view=new InstrumentedDraweeView(getContext());
  view.setHierarchy(gdh);
  return new VolleyDraweeHolder(getContext(),parent,view,getPerfListener());
}
