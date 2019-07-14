/** 
 * ################################### Getting/setting Log Settings ###################################
 */
private StaticBuffer getMarkerColumn(int partitionId,int bucketId){
  DataOutput out=manager.serializer.getDataOutput(1 + 4 + 4);
  out.putByte(MARKER_PREFIX);
  out.putInt(partitionId);
  out.putInt(bucketId);
  return out.getStaticBuffer();
}
