@CallSuper @Override public void bind(@NonNull ModelGroupHolder holder){
  iterateModels(holder,new IterateModelsCallback(){
    @Override public void onModel(    EpoxyModel model,    EpoxyViewHolder viewHolder,    int modelIndex){
      setViewVisibility(model,viewHolder);
      viewHolder.bind(model,null,Collections.emptyList(),modelIndex);
    }
  }
);
}
