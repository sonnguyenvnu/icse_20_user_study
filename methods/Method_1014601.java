/** 
 * Write x words from an array at an offset
 */
public boolean write(int offset,char[] src,int srcOffset,int count){
  if (offset + count > this.words.length || srcOffset >= src.length || count < 0 || offset < 0) {
    return false;
  }
  System.arraycopy(src,srcOffset,this.words,offset,count);
  return true;
}
