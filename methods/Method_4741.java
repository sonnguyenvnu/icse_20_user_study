/** 
 * Sets the state to STATE_READING_ID3_HEADER and resets the fields required for {@link #parseId3Header()}.
 */
private void setReadingId3HeaderState(){
  state=STATE_READING_ID3_HEADER;
  bytesRead=ID3_IDENTIFIER.length;
  sampleSize=0;
  id3HeaderBuffer.setPosition(0);
}
