@Override public LongStream events() throws IOException {
  return lines().flatMapToLong(line -> {
    String[] array=line.split(",",5);
    if (array.length <= 4) {
      return LongStream.empty();
    }
    long startBlock=Long.parseLong(array[1]);
    int size=Integer.parseInt(array[2]);
    int sequence=IntMath.divide(size,BLOCK_SIZE,RoundingMode.UP);
    char readWrite=Character.toLowerCase(array[3].charAt(0));
    return (readWrite == 'w') ? LongStream.empty() : LongStream.range(startBlock,startBlock + sequence);
  }
);
}
