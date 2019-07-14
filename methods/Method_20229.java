@Override public void bind(@NonNull ModelGroupHolder holder,@NonNull EpoxyModel<?> previouslyBoundModel){
  if (!(previouslyBoundModel instanceof EpoxyModelGroup)) {
    bind(holder);
    return;
  }
  final EpoxyModelGroup previousGroup=(EpoxyModelGroup)previouslyBoundModel;
  iterateModels(holder,new IterateModelsCallback(){
    @Override public void onModel(    EpoxyModel model,    EpoxyViewHolder viewHolder,    int modelIndex){
      setViewVisibility(model,viewHolder);
      if (modelIndex < previousGroup.models.size()) {
        EpoxyModel<?> previousModel=previousGroup.models.get(modelIndex);
        if (previousModel.id() == model.id()) {
          viewHolder.bind(model,previousModel,Collections.emptyList(),modelIndex);
          return;
        }
      }
      viewHolder.bind(model,null,Collections.emptyList(),modelIndex);
    }
  }
);
}
