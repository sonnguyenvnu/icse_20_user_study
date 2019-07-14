@Override public void train(String folderPath,String charsetName) throws IOException {
  IDataSet dataSet=new MemoryDataSet();
  dataSet.load(folderPath,charsetName);
  train(dataSet);
}
