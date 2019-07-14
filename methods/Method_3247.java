private static TreeMap<String,Vector> loadVectorMap(String modelFileName) throws IOException {
  VectorsReader reader=new VectorsReader(modelFileName);
  reader.readVectorFile();
  TreeMap<String,Vector> map=new TreeMap<String,Vector>();
  for (int i=0; i < reader.vocab.length; i++) {
    map.put(reader.vocab[i],new Vector(reader.matrix[i]));
  }
  return map;
}
