private ModelState createStateForPosition(int position){
  EpoxyModel<?> model=adapter.getCurrentModels().get(position);
  model.addedToAdapter=true;
  ModelState state=ModelState.build(model,position,immutableModels);
  ModelState previousValue=currentStateMap.put(state.id,state);
  if (previousValue != null) {
    int previousPosition=previousValue.position;
    EpoxyModel<?> previousModel=adapter.getCurrentModels().get(previousPosition);
    throw new IllegalStateException("Two models have the same ID. ID's must be unique!" + " Model at position " + position + ": " + model + " Model at position " + previousPosition + ": " + previousModel);
  }
  return state;
}
