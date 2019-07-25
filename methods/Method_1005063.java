@Override protected void map(final Element element,final Context context) throws IOException, InterruptedException {
  if (Math.random() < proportionToSample) {
    context.getCounter("Split points","Number sampled").increment(1L);
    final Pair<Key,Key> keyPair;
    try {
      keyPair=elementConverter.getKeysFromElement(element);
    }
 catch (    final AccumuloElementConversionException e) {
      throw new IllegalArgumentException(e.getMessage(),e);
    }
    final Value value;
    try {
      value=elementConverter.getValueFromElement(element);
    }
 catch (    final AccumuloElementConversionException e) {
      throw new IllegalArgumentException(e.getMessage(),e);
    }
    context.write(keyPair.getFirst(),value);
    if (null != keyPair.getSecond()) {
      context.write(keyPair.getSecond(),value);
    }
  }
 else {
    context.getCounter("Split points","Number not sampled").increment(1L);
  }
}
