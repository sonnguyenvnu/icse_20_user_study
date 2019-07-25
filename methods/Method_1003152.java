/** 
 * Remove all stored blocks for the given id.
 * @param id the id
 */
public void remove(byte[] id){
  ByteBuffer idBuffer=ByteBuffer.wrap(id);
  while (idBuffer.hasRemaining()) {
switch (idBuffer.get()) {
case 0:
      int len=DataUtils.readVarInt(idBuffer);
    idBuffer.position(idBuffer.position() + len);
  break;
case 1:
DataUtils.readVarInt(idBuffer);
long k=DataUtils.readVarLong(idBuffer);
map.remove(k);
break;
case 2:
DataUtils.readVarLong(idBuffer);
long k2=DataUtils.readVarLong(idBuffer);
remove(map.get(k2));
map.remove(k2);
break;
default :
throw DataUtils.newIllegalArgumentException("Unsupported id {0}",Arrays.toString(id));
}
}
}
