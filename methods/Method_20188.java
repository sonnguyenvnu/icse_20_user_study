private int findPositionOfDuplicate(List<EpoxyModel<?>> models,EpoxyModel<?> duplicateModel){
  int size=models.size();
  for (int i=0; i < size; i++) {
    EpoxyModel<?> model=models.get(i);
    if (model.id() == duplicateModel.id()) {
      return i;
    }
  }
  throw new IllegalArgumentException("No duplicates in list");
}
