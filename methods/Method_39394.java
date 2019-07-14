/** 
 * Takes given parameters references and returns reference set for given method or constructor.
 */
public BeanReferences[] resolveReferenceFromValues(final Executable methodOrCtor,final String... parameterReferences){
  BeanReferences[] references=convertRefToReferences(parameterReferences);
  if (references == null || references.length == 0) {
    references=buildDefaultReferences(methodOrCtor);
  }
  if (methodOrCtor.getParameterTypes().length != references.length) {
    throw new PetiteException("Different number of method parameters and references for: " + methodOrCtor.getDeclaringClass().getName() + '#' + methodOrCtor.getName());
  }
  removeAllDuplicateNames(references);
  return references;
}
