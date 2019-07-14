@Override public LongStream events() throws IOException {
  return lines().map(line -> line.split(" ",3)[1]).map(address -> address.substring(2)).mapToLong(address -> Long.parseLong(address,16));
}
