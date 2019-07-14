@Override public LongStream events() throws IOException {
  return lines().filter(line -> !line.isEmpty()).filter(line -> !line.equals("*")).mapToLong(Long::parseLong);
}
