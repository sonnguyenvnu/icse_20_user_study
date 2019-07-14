/** 
 * Find the model that has the given view type so we can create a view for that model. In most cases this value is a layout resource and we could simply inflate it, but to support  {@link EpoxyModelWithView} we can't assume the view type is a layout. In that case we need to lookupthe model so we can ask it to create a new view for itself. <p> To make this efficient, we rely on the RecyclerView implementation detail that  {@link BaseEpoxyAdapter#getItemViewType(int)} is called immediately before {@link BaseEpoxyAdapter#onCreateViewHolder(android.view.ViewGroup,int)} . We cache the last modelthat had its view type looked up, and unless that implementation changes we expect to have a very fast lookup for the correct model. <p> To be safe, we fallback to searching through all models for a view type match. This is slow and shouldn't be needed, but is a guard against recyclerview behavior changing.
 */
EpoxyModel<?> getModelForViewType(BaseEpoxyAdapter adapter,int viewType){
  if (lastModelForViewTypeLookup != null && getViewType(lastModelForViewTypeLookup) == viewType) {
    return lastModelForViewTypeLookup;
  }
  adapter.onExceptionSwallowed(new IllegalStateException("Last model did not match expected view type"));
  for (  EpoxyModel<?> model : adapter.getCurrentModels()) {
    if (getViewType(model) == viewType) {
      return model;
    }
  }
  HiddenEpoxyModel hiddenEpoxyModel=new HiddenEpoxyModel();
  if (viewType == hiddenEpoxyModel.getViewType()) {
    return hiddenEpoxyModel;
  }
  throw new IllegalStateException("Could not find model for view type: " + viewType);
}
