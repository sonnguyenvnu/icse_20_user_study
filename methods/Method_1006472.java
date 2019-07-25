/** 
 * Append the values from another array, no copy is made (use with care)
 * @param sa other array
 * @param startingIndex starting index in the other array
 * @param end endingIndex (exclusive) in the other array
 */
protected void append(RoaringArray sa,int startingIndex,int end){
  extendArray(end - startingIndex);
  for (int i=startingIndex; i < end; ++i) {
    this.keys[this.size]=sa.keys[i];
    this.values[this.size]=sa.values[i];
    this.size++;
  }
}
