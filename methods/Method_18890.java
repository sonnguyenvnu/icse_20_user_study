/** 
 * @return the list of props without taking deduplication or name cache adjustments into account.
 */
@Override public ImmutableList<PropModel> getRawProps(){
  return mSpecModel.getRawProps();
}
