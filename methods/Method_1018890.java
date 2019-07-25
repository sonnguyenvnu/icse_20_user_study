/** 
 * Get an individual amplification value.
 * @param index index of the frequency band to get
 * @return amplification value
 * @throws IllegalArgumentException if the index is outside of the allowed range
 */
public final float amp(int index){
  if (index >= 0 && index < bandCount) {
    return bandAmps[index];
  }
 else {
    throw new IllegalArgumentException("Invalid band index");
  }
}
