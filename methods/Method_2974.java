public void saveModel(String modelPath) throws IOException {
  ObjectOutput writer=new ObjectOutputStream(new GZIPOutputStream(IOUtil.newOutputStream(modelPath)));
  writer.writeObject(dependencyLabels);
  writer.writeObject(maps);
  writer.writeObject(options);
  writer.writeObject(shiftFeatureAveragedWeights);
  writer.writeObject(reduceFeatureAveragedWeights);
  writer.writeObject(leftArcFeatureAveragedWeights);
  writer.writeObject(rightArcFeatureAveragedWeights);
  writer.writeInt(dependencySize);
  writer.close();
}
