private void iterateModels(ModelGroupHolder holder,IterateModelsCallback callback){
  holder.bindGroupIfNeeded(this);
  int modelCount=models.size();
  for (int i=0; i < modelCount; i++) {
    callback.onModel(models.get(i),holder.getViewHolders().get(i),i);
  }
}
