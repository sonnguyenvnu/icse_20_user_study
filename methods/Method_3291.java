public boolean open(String model,int nbest,int vlevel,double costFactor){
  try {
    InputStream stream=IOUtil.newInputStream(model);
    return open(stream,nbest,vlevel,costFactor);
  }
 catch (  Exception e) {
    return false;
  }
}
