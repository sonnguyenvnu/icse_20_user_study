/** 
 * Unsafe version of  {@link #m_overlappingObjects(B3OverlappingObject.Buffer) m_overlappingObjects}. 
 */
public static void nm_overlappingObjects(long struct,B3OverlappingObject.Buffer value){
  memPutAddress(struct + B3AABBOverlapData.M_OVERLAPPINGOBJECTS,value.address());
  nm_numOverlappingObjects(struct,value.remaining());
}
