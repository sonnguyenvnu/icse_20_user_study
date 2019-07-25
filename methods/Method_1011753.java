private void commit(){
  ListSequence.fromList(myNewChanges).visitAll(new IVisitor<ModelChange>(){
    public void visit(    ModelChange it){
      myChangeSet.add(it);
    }
  }
);
  myChangeSet.buildNodeMaps(myOldToNewMap);
  ListSequence.fromList(myNewChanges).clear();
  MapSequence.fromMap(myOldToNewMap).clear();
}
