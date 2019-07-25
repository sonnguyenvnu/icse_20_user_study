@Override public void characters(final char ch[],final int start,final int length){
  if (this.parsingItem || this.parsingChannel) {
    this.buffer.append(ch,start,length);
  }
}
