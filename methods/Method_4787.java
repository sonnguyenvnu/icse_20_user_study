private void resetBufferForSize(int newSize){
  sampleDataBuffer.reset(newSize);
  sampleBitArray.reset(sampleDataBuffer.data);
}
