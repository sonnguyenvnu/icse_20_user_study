@Override public scala.collection.Iterator<Map.Entry<Key,Value>> compute(final Partition split,final TaskContext context){
  final ByteArrayInputStream bais=new ByteArrayInputStream(serialisedConfiguration);
  final Configuration configuration=new Configuration();
  try {
    configuration.readFields(new DataInputStream(bais));
    bais.close();
  }
 catch (  final IOException e) {
    throw new RuntimeException("IOException deserialising Configuration from byte array",e);
  }
  return new InterruptibleIterator<>(context,JavaConversions.asScalaIterator(new RFileReaderIterator(split,context,configuration,auths)));
}
