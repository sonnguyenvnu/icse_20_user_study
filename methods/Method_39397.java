private BeanReferences[] updateReferencesWithDefaultsIfNeeded(final Executable methodOrCtor,BeanReferences[] references){
  BeanReferences[] defaultReferences=buildDefaultReferences(methodOrCtor);
  if (references == null || references.length == 0) {
    references=defaultReferences;
  }
  if (methodOrCtor.getParameterTypes().length != references.length) {
    throw new PetiteException("Different number of parameters and references for: " + methodOrCtor.getName());
  }
  for (int i=0; i < references.length; i++) {
    BeanReferences parameterReferences=references[i];
    if (parameterReferenceIsNotSet(parameterReferences)) {
      references[i]=defaultReferences[i];
    }
  }
  return references;
}
