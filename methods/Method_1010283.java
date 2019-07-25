/** 
 * @param c concept to look up
 * @return -1 if this index is not aware of the concept, or concept is null
 */
default int index(@Nullable SAbstractConcept c){
  return index(c,-1);
}
