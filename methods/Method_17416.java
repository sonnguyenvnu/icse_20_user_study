@Override public LongStream events() throws IOException {
  return lines().map(uuid -> new BigInteger(uuid,16)).mapToLong(num -> num.shiftRight(64).longValue() ^ num.longValue());
}
