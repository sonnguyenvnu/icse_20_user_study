@NotNull public static _SAbstractConcept wrap(@NotNull SAbstractConcept concept){
  if (concept instanceof SInterfaceConcept) {
    return new _SInterfaceConcept((SInterfaceConcept)concept);
  }
 else   if (concept instanceof SConcept) {
    return new _SConcept((SConcept)concept);
  }
  throw new UnknownConceptException(concept);
}
