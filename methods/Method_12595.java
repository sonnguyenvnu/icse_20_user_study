/** 
 * ??context?????????segmentBuff 
 * @param reader
 * @return ???????????????
 * @throws java.io.IOException
 */
int fillBuffer(Reader reader) throws IOException {
  int readCount=0;
  if (this.buffOffset == 0) {
    readCount=reader.read(segmentBuff);
  }
 else {
    int offset=this.available - this.cursor;
    if (offset > 0) {
      System.arraycopy(this.segmentBuff,this.cursor,this.segmentBuff,0,offset);
      readCount=offset;
    }
    readCount+=reader.read(this.segmentBuff,offset,BUFF_SIZE - offset);
  }
  this.available=readCount;
  this.cursor=0;
  return readCount;
}
