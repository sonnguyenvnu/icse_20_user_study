@Override public int getModelPosition(@NonNull EpoxyModel<?> targetModel){
  int size=getCurrentModels().size();
  for (int i=0; i < size; i++) {
    EpoxyModel<?> model=getCurrentModels().get(i);
    if (model.id() == targetModel.id()) {
      return i;
    }
  }
  return -1;
}
