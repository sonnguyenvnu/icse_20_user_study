@Override public MappeableContainer ixor(final MappeableRunContainer x){
  if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
    long[] b=this.bitmap.array();
    for (int rlepos=0; rlepos < x.nbrruns; ++rlepos) {
      int start=toIntUnsigned(x.getValue(rlepos));
      int end=start + toIntUnsigned(x.getLength(rlepos)) + 1;
      int prevOnes=Util.cardinalityInBitmapRange(b,start,end);
      Util.flipBitmapRange(b,start,end);
      updateCardinality(prevOnes,end - start - prevOnes);
    }
  }
 else {
    for (int rlepos=0; rlepos < x.nbrruns; ++rlepos) {
      int start=toIntUnsigned(x.getValue(rlepos));
      int end=start + toIntUnsigned(x.getLength(rlepos)) + 1;
      int prevOnes=cardinalityInRange(start,end);
      BufferUtil.flipBitmapRange(this.bitmap,start,end);
      updateCardinality(prevOnes,end - start - prevOnes);
    }
  }
  if (this.getCardinality() > MappeableArrayContainer.DEFAULT_MAX_SIZE) {
    return this;
  }
 else {
    return toArrayContainer();
  }
}
