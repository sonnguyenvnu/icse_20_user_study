private void ensureCapacity(int newrindex){
  if (reverse.length < newrindex) {
    int[] tmp=new int[Math.max(newrindex,6 * reverse.length / 5)];
    System.arraycopy(reverse,0,tmp,0,rindex);
    this.reverse=tmp;
  }
}
