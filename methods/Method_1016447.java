public long size(){
  if (this.responseHeader != null && this.responseHeader.getContentLengthLong() != -1) {
    return this.responseHeader.getContentLengthLong();
  }
  if (this.content != null)   return this.content.length;
  return -1;
}
