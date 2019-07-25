/** 
 * Construct a union operator populated with the given byte array image of an HllSketch.
 * @param byteArray the given byte array
 * @return a union operator populated with the given byte array image of an HllSketch.
 */
public static final Union heapify(final byte[] byteArray){
  return heapify(Memory.wrap(byteArray));
}
