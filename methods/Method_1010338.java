@NotNull private static Set<SAbstractConcept> add(@Nullable Set<SAbstractConcept> values,@NotNull SAbstractConcept abstractConcept){
  Set<SAbstractConcept> descendants=new LinkedHashSet<>(values == null ? Collections.emptySet() : values);
  descendants.add(abstractConcept);
  return Collections.unmodifiableSet(descendants);
}
