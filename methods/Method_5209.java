private static boolean hasCea608Track(List<AdaptationSet> adaptationSets,int[] adaptationSetIndices){
  for (  int i : adaptationSetIndices) {
    List<Descriptor> descriptors=adaptationSets.get(i).accessibilityDescriptors;
    for (int j=0; j < descriptors.size(); j++) {
      Descriptor descriptor=descriptors.get(j);
      if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri)) {
        return true;
      }
    }
  }
  return false;
}
