public double next(int val){
  if (window.size() == maxSize) {
    previousSum-=window.remove();
  }
  window.add(val);
  previousSum+=val;
  return previousSum / window.size();
}
