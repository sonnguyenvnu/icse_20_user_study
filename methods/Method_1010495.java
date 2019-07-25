public void replace(SModelData modelData){
  assertCanChange();
  if (!(modelData instanceof DefaultSModel)) {
    throw new IllegalArgumentException();
  }
  replaceModel((DefaultSModel)modelData,ModelLoadingState.FULLY_LOADED);
  myHeader=((DefaultSModel)modelData).getSModelHeader();
}
