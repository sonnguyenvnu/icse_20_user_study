@CallSuper @Override public void onViewAttachedToWindow(ModelGroupHolder holder){
  iterateModels(holder,new IterateModelsCallback(){
    @Override public void onModel(    EpoxyModel model,    EpoxyViewHolder viewHolder,    int modelIndex){
      model.onViewAttachedToWindow(viewHolder.objectToBind());
    }
  }
);
}
