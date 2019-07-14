private boolean isPinnedViewType(RecyclerView parent,int adapterPosition,int viewType){
  PinnedHeaderCreator pinnedHeaderCreator=mTypePinnedHeaderFactories.get(viewType);
  return pinnedHeaderCreator != null && pinnedHeaderCreator.create(parent,adapterPosition);
}
