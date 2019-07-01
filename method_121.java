public boolean _XXXXX_(byte[] cmp){
  if (this.result == null || this.result.length == 0) {
    finish();
  }
  return md.isEqual(this.result,cmp);
}