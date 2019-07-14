private void reset(){
  filter=filterType.construct(keys,bitsPerKey);
  checkState(filter.supportsAdd(),"Filter must support additions");
}
