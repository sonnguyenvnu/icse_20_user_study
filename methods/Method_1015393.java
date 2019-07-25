/** 
 * The default implementation assumes that  {@link ByteBuffer#flip()}  or {@link ByteBuffer#rewind()} was called onbuf before invoking this callback
 * @param sender
 * @param buf
 */
public void receive(Address sender,ByteBuffer buf){
  Util.bufferToArray(sender,buf,this);
}
