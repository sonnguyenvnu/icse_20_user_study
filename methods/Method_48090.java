public static List<ByteBuffer> convert(List<StaticBuffer> keys){
  return keys.stream().map(StaticBuffer::asByteBuffer).collect(Collectors.toCollection(() -> new ArrayList<>(keys.size())));
}
