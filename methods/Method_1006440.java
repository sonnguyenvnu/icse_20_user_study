@Override public MappeableContainer xor(MappeableBitmapContainer x){
  MappeableBitmapContainer answer=x.clone();
  for (int rlepos=0; rlepos < this.nbrruns; ++rlepos) {
    int start=toIntUnsigned(this.getValue(rlepos));
    int end=start + toIntUnsigned(this.getLength(rlepos)) + 1;
    int prevOnes=answer.cardinalityInRange(start,end);
    BufferUtil.flipBitmapRange(answer.bitmap,start,end);
    answer.updateCardinality(prevOnes,end - start - prevOnes);
  }
  if (answer.getCardinality() > MappeableArrayContainer.DEFAULT_MAX_SIZE) {
    return answer;
  }
 else {
    return answer.toArrayContainer();
  }
}
