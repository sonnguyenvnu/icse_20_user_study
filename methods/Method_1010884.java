List<Pair<SNodeId,SReferenceLink>> pairs(){
  ArrayList<Pair<SNodeId,SReferenceLink>> rv=new ArrayList<>(myNodes.length);
  for (int i=0; i < myNodes.length; i++) {
    rv.add(new Pair<>(myNodes[i],myAssociations[i]));
  }
  return rv;
}
