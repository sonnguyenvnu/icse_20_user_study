@Override public void configure(AnnotatedElement element){
  List<Weighted<Generator<?>>> candidates=new ArrayList<>(composed);
  for (Iterator<Weighted<Generator<?>>> it=candidates.iterator(); it.hasNext(); ) {
    try {
      it.next().item.configure(element);
    }
 catch (    GeneratorConfigurationException e) {
      it.remove();
    }
  }
  installCandidates(candidates,element);
}
