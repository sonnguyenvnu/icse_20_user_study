/** 
 * Sets data to be parsed by libflac.
 * @param byteBufferData Source {@link ByteBuffer}
 */
public void setData(ByteBuffer byteBufferData){
  this.byteBufferData=byteBufferData;
  this.extractorInput=null;
  this.tempBuffer=null;
}
