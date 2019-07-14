/** 
 * ?CRF++????HanLP??
 * @param modelFile
 * @throws IOException
 */
private void convert(String modelFile) throws IOException {
  this.model=new LogLinearModel(modelFile + ".txt",modelFile);
}
