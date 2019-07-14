private void initCache(File cache) throws FileNotFoundException {
  this.cache=cache;
  out=new DataOutputStream(new FileOutputStream(cache));
}
