@Override public boolean intersects(RunContainer x){
  int rlepos=0;
  int xrlepos=0;
  int start=toIntUnsigned(this.getValue(rlepos));
  int end=start + toIntUnsigned(this.getLength(rlepos)) + 1;
  int xstart=toIntUnsigned(x.getValue(xrlepos));
  int xend=xstart + toIntUnsigned(x.getLength(xrlepos)) + 1;
  while ((rlepos < this.nbrruns) && (xrlepos < x.nbrruns)) {
    if (end <= xstart) {
      if (ENABLE_GALLOPING_AND) {
        rlepos=skipAhead(this,rlepos,xstart);
      }
 else {
        ++rlepos;
      }
      if (rlepos < this.nbrruns) {
        start=toIntUnsigned(this.getValue(rlepos));
        end=start + toIntUnsigned(this.getLength(rlepos)) + 1;
      }
    }
 else     if (xend <= start) {
      if (ENABLE_GALLOPING_AND) {
        xrlepos=skipAhead(x,xrlepos,start);
      }
 else {
        ++xrlepos;
      }
      if (xrlepos < x.nbrruns) {
        xstart=toIntUnsigned(x.getValue(xrlepos));
        xend=xstart + toIntUnsigned(x.getLength(xrlepos)) + 1;
      }
    }
 else {
      return true;
    }
  }
  return false;
}
