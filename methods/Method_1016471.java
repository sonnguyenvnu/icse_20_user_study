public final void finish(){
  if (this.finished)   return;
  this.finished=true;
  ByteCount.addAccountCount(this.byteCountAccountName,this.byteCount);
}
