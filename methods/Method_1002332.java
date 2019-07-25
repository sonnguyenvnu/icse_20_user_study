private long offset(long index){
  return ARRAY_BASE + ((index & mask) << ELEMENT_SHIFT);
}
