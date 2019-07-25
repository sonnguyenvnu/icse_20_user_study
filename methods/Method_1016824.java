/** 
 * Really this should be 'protected', but isn't for historical reasons.  
 */
public Instance pipe(Instance inst){
  throw new UnsupportedOperationException("Pipes of class " + this.getClass().getName() + " do not guarantee one-to-one mapping of Instances.  Use 'newIteratorFrom' method instead.");
}
