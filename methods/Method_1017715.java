@Override public void configure(AnnotatedType annotatedType){
  List<Weighted<Generator<?>>> candidates=new ArrayList<>(composed);
  for (Iterator<Weighted<Generator<?>>> it=candidates.iterator(); it.hasNext(); ) {
    try {
      it.next().item.configure(annotatedType);
    }
 catch (    GeneratorConfigurationException e) {
      it.remove();
    }
  }
  installCandidates(candidates,annotatedType);
}
