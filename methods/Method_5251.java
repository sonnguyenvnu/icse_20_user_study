protected AdaptationSet buildAdaptationSet(int id,int contentType,List<Representation> representations,List<Descriptor> accessibilityDescriptors,List<Descriptor> supplementalProperties){
  return new AdaptationSet(id,contentType,representations,accessibilityDescriptors,supplementalProperties);
}
