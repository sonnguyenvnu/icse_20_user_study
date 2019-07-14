private void writeLastSampleToOutput(int size,long lastSampleTimestamp){
  outputBuffer.setPosition(0);
  trackOutput.sampleData(outputBuffer,size);
  trackOutput.sampleMetadata(lastSampleTimestamp,C.BUFFER_FLAG_KEY_FRAME,size,0,null);
}
