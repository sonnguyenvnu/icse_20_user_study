/** 
 * Returns the data limit, or  {@link C#POSITION_UNSET} if the data bounds have not been set. 
 */
public long getDataLimit(){
  return hasDataBounds() ? (dataStartPosition + dataSize) : C.POSITION_UNSET;
}
