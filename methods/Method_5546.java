private void releaseInputBuffer(CeaInputBuffer inputBuffer){
  inputBuffer.clear();
  availableInputBuffers.add(inputBuffer);
}
