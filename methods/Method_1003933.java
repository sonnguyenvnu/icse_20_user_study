private void allocate(int i){
  if (buffer[i] == null) {
    buffer[i]=new long[bufferSize];
  }
}
