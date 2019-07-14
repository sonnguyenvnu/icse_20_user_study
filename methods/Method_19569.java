private List<DemoListDataModel> getDataModels(@Nullable int[] indices){
  List<DemoListDataModel> dataModels=DATA_MODELS;
  if (indices == null) {
    return dataModels;
  }
  for (int i=0; i < indices.length; i++) {
    dataModels=dataModels.get(indices[i]).datamodels;
  }
  return dataModels;
}
