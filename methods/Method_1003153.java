/** 
 * Calculate the number of data bytes for the given id. As the length is encoded in the id, this operation does not cause any reads in the map.
 * @param id the id
 * @return the length
 */
public long length(byte[] id){
  ByteBuffer idBuffer=ByteBuffer.wrap(id);
  long length=0;
  while (idBuffer.hasRemaining()) {
switch (idBuffer.get()) {
case 0:
      int len=DataUtils.readVarInt(idBuffer);
    idBuffer.position(idBuffer.position() + len);
  length+=len;
break;
case 1:
length+=DataUtils.readVarInt(idBuffer);
DataUtils.readVarLong(idBuffer);
break;
case 2:
length+=DataUtils.readVarLong(idBuffer);
DataUtils.readVarLong(idBuffer);
break;
default :
throw DataUtils.newIllegalArgumentException("Unsupported id {0}",Arrays.toString(id));
}
}
return length;
}
