public Writer create(long entries,double loadFactor) throws IOException {
  return new Writer(SlabAllocator.allocate(requiredBytes(entries,loadFactor)));
}
