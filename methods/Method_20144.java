private void prepareStateForDiff(){
  oldStateList.clear();
  oldStateMap.clear();
  ArrayList<ModelState> tempList=oldStateList;
  oldStateList=currentStateList;
  currentStateList=tempList;
  Map<Long,ModelState> tempMap=oldStateMap;
  oldStateMap=currentStateMap;
  currentStateMap=tempMap;
  for (  ModelState modelState : oldStateList) {
    modelState.pair=null;
  }
  int modelCount=adapter.getCurrentModels().size();
  currentStateList.ensureCapacity(modelCount);
  for (int i=0; i < modelCount; i++) {
    currentStateList.add(createStateForPosition(i));
  }
}
