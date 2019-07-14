/** 
 * @return the Stream of unique parameters from all methods excluding current method. 
 */
public static Stream<PsiParameter> getPsiParameterStream(@Nullable PsiMethod currentMethod,PsiMethod[] allMethods){
  return Stream.of(allMethods).filter(psiMethod -> !psiMethod.equals(currentMethod)).map(PsiMethod::getParameterList).map(PsiParameterList::getParameters).flatMap(Stream::of).filter(distinctByKey(PsiParameter::getName));
}
