/** 
 * Use of the builder is not expected once this method has been invoked. XXX perhaps, ConceptIndex return value would be better, jsut need to regenerate a lot.
 */
public ConceptSwitchIndex seal(){
  assert !myIsSealed;
  myIsSealed=true;
  myConcepts.compact();
  return new ConceptSwitchIndex(myConcepts);
}
