@Override protected Stream<String> process(final Stream<? extends Element> stream,final HBaseStore store){
  final ElementSerialisation serialiser=new ElementSerialisation(store.getSchema());
  return stream.map(element -> {
    try {
      return serialiser.getRowKeys(element);
    }
 catch (    final SerialisationException e) {
      throw new RuntimeException("Unable to serialise element: " + element,e);
    }
  }
).flatMap(p -> null == p.getSecond() ? Stream.of(p.getFirst()) : Stream.of(p.getFirst(),p.getSecond())).map(Base64::encodeBase64).map(StringUtil::toString);
}
