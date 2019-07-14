private static Comparator<MethodTree> comparingArity(){
  return comparingInt(ParameterTrie::getMethodTreeArity);
}
