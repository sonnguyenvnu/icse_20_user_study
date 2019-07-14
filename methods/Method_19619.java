private void setTitleFromIndices(int[] indices){
  List<DemoListActivity.DemoListDataModel> dataModels=DemoListActivity.DATA_MODELS;
  for (int i=0; i < indices.length - 1; i++) {
    dataModels=dataModels.get(indices[i]).datamodels;
  }
  final String title=dataModels.get(indices[indices.length - 1]).name;
  setTitle(title);
}
