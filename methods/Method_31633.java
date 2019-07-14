@Override public Reader read(){
  try {
    return new InputStreamReader(assetManager.open(fileNameWithAbsolutePath),encoding.newDecoder());
  }
 catch (  IOException e) {
    throw new FlywayException("Unable to read asset: " + getAbsolutePath(),e);
  }
}
