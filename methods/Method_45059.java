private void buildSelectionToUniqueStrTreeMap(){
  TreeMap<Selection,String> treeMap=new TreeMap<>();
  Map<String,Selection> definitionToSelectionMap=linkProvider.getDefinitionToSelectionMap();
  Map<String,Set<Selection>> referenceToSelectionsMap=linkProvider.getReferenceToSelectionsMap();
  for (  String key : definitionToSelectionMap.keySet()) {
    Selection selection=definitionToSelectionMap.get(key);
    treeMap.put(selection,key);
  }
  for (  String key : referenceToSelectionsMap.keySet()) {
    for (    Selection selection : referenceToSelectionsMap.get(key)) {
      treeMap.put(selection,key);
    }
  }
  selectionToUniqueStrTreeMap=treeMap;
}
