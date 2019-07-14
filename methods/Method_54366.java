/** 
 * Unsafe version of  {@link #m_segmentationMaskValues(int) m_segmentationMaskValues}. 
 */
public static IntBuffer nm_segmentationMaskValues(long struct,int capacity){
  return memIntBuffer(memGetAddress(struct + B3CameraImageData.M_SEGMENTATIONMASKVALUES),capacity);
}
