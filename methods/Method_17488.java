@Override public void record(long key){
  policyStats.recordOperation();
  Node node=data.get(key);
  if (node == null) {
    node=new Node(key);
    data.put(key,node);
    onNonResidentHir(node);
  }
 else   if (node.status == Status.LIR) {
    onLir(node);
  }
 else   if (node.status == Status.HIR_RESIDENT) {
    onResidentHir(node);
  }
 else   if (node.status == Status.HIR_NON_RESIDENT) {
    node.removeFrom(StackType.NR);
    onNonResidentHir(node);
  }
 else {
    throw new IllegalStateException();
  }
}
