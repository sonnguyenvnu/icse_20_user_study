public int getFileReference(Object parentObject){
  int reference=lastReferenceId++;
  parentObjectReferences.put(reference,parentObject);
  return reference;
}
