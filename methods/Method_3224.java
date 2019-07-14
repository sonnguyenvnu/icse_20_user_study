@Override public void rewind(int numThreads,int id) throws IOException {
  super.rewind(numThreads,id);
  raf.seek(raf.length() / 4 / numThreads * id * 4);
}
