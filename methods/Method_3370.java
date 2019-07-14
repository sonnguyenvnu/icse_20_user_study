/** 
 * Allows external extension of the valiue creators.
 * @param vc another value creator to take into account for trying to create values
 */
public static void registerValueCreator(ValueCreator vc){
  valueCreators.add(vc);
}
