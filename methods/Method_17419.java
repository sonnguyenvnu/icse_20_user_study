@Override public LongStream events() throws IOException {
  return lines().map(line -> line.split(" ")).filter(array -> array[3].equals("GETVIDEO")).mapToLong(array -> Hashing.murmur3_128().hashUnencodedChars(array[4]).asLong());
}
