public final char next(){
  int index=++bp;
  return ch=(index >= this.len ? EOI : text.charAt(index));
}
