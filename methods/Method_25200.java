@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitStringConcatenateAssignment(StringConcatenateAssignmentNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness value=visitStringConcatenateAssignment();
  return noStoreChanges(value,input);
}
