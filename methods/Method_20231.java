@CallSuper @Override public void onViewDetachedFromWindow(ModelGroupHolder holder){
  iterateModels(holder,new IterateModelsCallback(){
    @Override public void onModel(    EpoxyModel model,    EpoxyViewHolder viewHolder,    int modelIndex){
      model.onViewDetachedFromWindow(viewHolder.objectToBind());
    }
  }
);
}
