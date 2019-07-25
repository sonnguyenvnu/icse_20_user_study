/** 
 * Use the supplied WalkedPath, in the evaluation of each of our PathElements. If our PathElements contained a TransposePathElement, we may return null.
 * @param walkedPath used to lookup/evaluate PathElement references values like "&1(2)"
 * @return null or fully evaluated Strings, possibly with concrete array references like "photos.[3]"
 */
public List<String> evaluate(WalkedPath walkedPath){
  List<String> strings=new ArrayList<>(elements.size());
  for (  EvaluatablePathElement pathElement : elements) {
    String evaledLeafOutput=pathElement.evaluate(walkedPath);
    if (evaledLeafOutput == null) {
      return null;
    }
    strings.add(evaledLeafOutput);
  }
  return strings;
}
