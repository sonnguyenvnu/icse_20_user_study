/** 
 * @generate Serial_bufferUntil.xml
 * @webref serial:serial
 * @usage web_application
 * @param inByte the value to buffer until
 */
public void bufferUntil(int inByte){
  bufferUntilSize=0;
  bufferUntilByte=(byte)inByte;
}
