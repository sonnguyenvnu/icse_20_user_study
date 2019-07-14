public boolean isTerminalState(){
  if (stackEmpty()) {
    if (bufferEmpty() || bufferHead == rootIndex) {
      return true;
    }
  }
  return false;
}
