private void trim(long time){
  while (!byteList.isEmpty() && (time - byteList.getFirst().time) > timeGranularity) {
    byteList.removeFirst();
  }
}
