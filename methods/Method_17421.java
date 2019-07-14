@Override public LongStream events() throws IOException {
  return lines().map(this::parseRequest).filter(Objects::nonNull).mapToLong(path -> Hashing.murmur3_128().hashUnencodedChars(path).asLong());
}
