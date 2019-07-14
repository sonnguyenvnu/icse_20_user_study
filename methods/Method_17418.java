@Override public LongStream events() throws IOException {
  return lines().map(line -> line.split(",",6)).mapToLong(array -> Long.parseLong(array[4]));
}
