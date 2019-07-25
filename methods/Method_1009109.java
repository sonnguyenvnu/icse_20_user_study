void next(){
  fRange.add(fDifference);
  if (fDifference != null) {
    if (fIndex < fArray.length)     fDifference=fArray[fIndex++];
 else     fDifference=null;
  }
}
