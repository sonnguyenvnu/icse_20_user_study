/** 
 * Subclasses must call this method when a unicast or multicast message has been received.
 */
public void receive(Address sender,byte[] data,int offset,int length){
  if (data == null)   return;
  if (Objects.equals(local_physical_addr,sender))   return;
  if (length < Global.SHORT_SIZE + Global.BYTE_SIZE)   return;
  short version=Bits.readShort(data,offset);
  if (!versionMatch(version,sender))   return;
  offset+=Global.SHORT_SIZE;
  byte flags=data[offset];
  offset+=Global.BYTE_SIZE;
  boolean is_message_list=(flags & LIST) == LIST, multicast=(flags & MULTICAST) == MULTICAST;
  ByteArrayDataInputStream in=new ByteArrayDataInputStream(data,offset,length);
  if (is_message_list)   handleMessageBatch(in,multicast);
 else   handleSingleMessage(in,multicast);
}
