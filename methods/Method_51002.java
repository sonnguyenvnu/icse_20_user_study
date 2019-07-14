@Override public void insert(int beginLine,int beginColumn,final String textToInsert){
  try {
    tryToInsertIntoFile(beginLine,beginColumn,textToInsert);
  }
 catch (  final IOException e) {
    LOG.log(Level.WARNING,"An exception occurred when inserting into file " + filePath);
  }
}
