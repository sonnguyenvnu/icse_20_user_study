@Override public boolean next() throws SQLServerException {
  try {
    currentLine=fileReader.readLine();
  }
 catch (  IOException e) {
    throw new SQLServerException(e.getMessage(),null,0,e);
  }
  return (null != currentLine);
}
