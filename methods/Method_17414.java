@Override public LongStream events() throws IOException {
  return lines().flatMapToLong(line -> {
    String[] array=line.split(" ",3);
    long startBlock=Long.parseLong(array[0]);
    int sequence=Integer.parseInt(array[1]);
    return LongStream.range(startBlock,startBlock + sequence);
  }
);
}
