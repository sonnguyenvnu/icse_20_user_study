private static Descriptor findAdaptationSetSwitchingProperty(List<Descriptor> descriptors){
  for (int i=0; i < descriptors.size(); i++) {
    Descriptor descriptor=descriptors.get(i);
    if ("urn:mpeg:dash:adaptation-set-switching:2016".equals(descriptor.schemeIdUri)) {
      return descriptor;
    }
  }
  return null;
}
