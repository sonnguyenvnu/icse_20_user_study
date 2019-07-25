/** 
 * @param index record which should be read
 * @param b destination array
 * @param start offset in b to store data
 * @throws IOException
 */
public final synchronized void get(final long index,final byte[] b,final int start) throws IOException {
  assert b.length - start >= this.recordsize;
  final long filesize=filesize();
  final long s=filesize + this.buffercount;
  if (index >= s)   throw new IndexOutOfBoundsException("kelondroEcoFS.get(" + index + ") outside bounds (" + s + ")");
  final int q=inBuffer(index,filesize);
  if (q < 0) {
    this.raf.seek(this.recordsize * index);
    this.raf.readFully(b,start,this.recordsize);
    return;
  }
  System.arraycopy(this.buffer,q * this.recordsize,b,start,this.recordsize);
}
