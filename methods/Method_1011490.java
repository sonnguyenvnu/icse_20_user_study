@Override public IInterfacedFinder instantiate(int token) throws IllegalArgumentException {
switch (token) {
case 0:
    return new ConceptAncestors_Finder();
case 1:
  return new ConceptDescendants_Finder();
case 2:
return new ConceptInstances_Finder();
case 3:
return new DerivedConcepts_Finder();
case 4:
return new DerivedInterfaceConcepts_Finder();
case 5:
return new ExactConceptInstances_Finder();
case 6:
return new ImplementingConcepts_Finder();
case 7:
return new LinkInstances_Finder();
case 8:
return new NodeUsages_Finder();
case 9:
return new PropertyInstances_Finder();
case 10:
return new StraightDescendants_Finder();
default :
throw new IllegalArgumentException(String.format("Illegal identifier of a finder implementation: %d",token));
}
}
