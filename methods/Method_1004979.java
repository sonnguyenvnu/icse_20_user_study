@Override public Element deserialise(final byte[] bytes) throws SerialisationException {
  final String group=getGroup(bytes);
  if (null != schema.getEntity(group)) {
    return entitySerialiser.deserialise(bytes);
  }
  return edgeSerialiser.deserialise(bytes);
}
