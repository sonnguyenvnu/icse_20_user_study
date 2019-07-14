private static MaxHistory readHistory(File storedResults) throws CouldNotReadCoreException {
  try {
    FileInputStream file=new FileInputStream(storedResults);
    try {
      ObjectInputStream stream=new ObjectInputStream(file);
      try {
        return (MaxHistory)stream.readObject();
      }
  finally {
        stream.close();
      }
    }
  finally {
      file.close();
    }
  }
 catch (  Exception e) {
    throw new CouldNotReadCoreException(e);
  }
}
