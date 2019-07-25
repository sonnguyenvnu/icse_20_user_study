public ByteBuffer trim(final int start,final int len){
  if (start + len > this.length)   throw new IndexOutOfBoundsException("trim: start + len > length; this.offset = " + this.offset + ", this.length = " + this.length + ", start = " + start + ", len = " + len);
  this.offset=this.offset + start;
  this.length=len;
  return this;
}
