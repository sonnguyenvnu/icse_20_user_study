public void commit(){
  ListSequence.fromList(myNewChanges).visitAll(new IVisitor<ModelChange>(){
    public void visit(    ModelChange it){
      myChangeSet.add(it);
    }
  }
);
  ListSequence.fromList(myNewChanges).clear();
  myChangeSet.fillRootToChange();
}
