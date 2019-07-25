@Override protected Stream<String> process(final Stream<? extends Element> stream,final AccumuloStore store){
  final AccumuloElementConverter converter=store.getKeyPackage().getKeyConverter();
  return stream.map(converter::getRowKeysFromElement).flatMap(p -> null == p.getSecond() ? Stream.of(p.getFirst()) : Stream.of(p.getFirst(),p.getSecond())).map(Base64::encodeBase64).map(StringUtil::toString);
}
