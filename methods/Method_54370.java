/** 
 * Unsafe version of  {@link #m_segmentationMaskValues(IntBuffer) m_segmentationMaskValues}. 
 */
public static void nm_segmentationMaskValues(long struct,IntBuffer value){
  memPutAddress(struct + B3CameraImageData.M_SEGMENTATIONMASKVALUES,memAddress(value));
}
