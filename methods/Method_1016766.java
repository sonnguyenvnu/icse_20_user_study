public boolean intersects(Span r){
  if (!(r instanceof StringSpan))   return false;
  StringSpan sr=(StringSpan)r;
  return (sr.document == this.document && !(sr.end < this.start || sr.start > this.end));
}
