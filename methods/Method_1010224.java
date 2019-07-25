public boolean resolve(SReference reference,SRepository repository){
  SNode sourceNode=reference.getSourceNode();
  if (sourceNode == null) {
    return false;
  }
  for (  IResolver nextResolver : ListSequence.fromList(myResolvers)) {
    if (nextResolver.resolve(reference,sourceNode,repository)) {
      return true;
    }
  }
  return false;
}
