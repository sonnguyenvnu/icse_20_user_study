/** 
 * Parses the Id3 header.
 */
private void parseId3Header(){
  id3Output.sampleData(id3HeaderBuffer,ID3_HEADER_SIZE);
  id3HeaderBuffer.setPosition(ID3_SIZE_OFFSET);
  setReadingSampleState(id3Output,0,ID3_HEADER_SIZE,id3HeaderBuffer.readSynchSafeInt() + ID3_HEADER_SIZE);
}
