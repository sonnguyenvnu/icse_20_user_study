private int size(SourceOfRandomness random,GenerationStatus status){
  return sizeRange != null ? random.nextInt(sizeRange.min(),sizeRange.max()) : status.size();
}
