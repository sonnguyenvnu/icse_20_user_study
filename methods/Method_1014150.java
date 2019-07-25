protected void activate(Map<String,Object> properties){
  Object serviceRanking=properties.get(Constants.SERVICE_RANKING);
  if (serviceRanking instanceof Integer) {
    rank=(Integer)serviceRanking;
  }
 else {
    rank=0;
  }
  itemFactorys.forEach(itemFactory -> dispatchBindingsPerItemType(null,itemFactory.getSupportedItemTypes()));
  for (  String modelName : modelRepository.getAllModelNamesOfType("items")) {
    modelChanged(modelName,EventType.ADDED);
  }
  modelRepository.addModelRepositoryChangeListener(this);
  active=true;
}
