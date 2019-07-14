public void recordItem(long item){
  if (this.ghostCache.countItem(hashFunc.fpaux.value) < sampleSize) {
    this.ghostCache.addItem(hashFunc.fpaux.value);
  }
}
