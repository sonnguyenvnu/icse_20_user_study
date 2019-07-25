public CustomXmlDataStorage factory(){
  try {
    return new CustomXmlDataStorageImpl();
  }
 catch (  InvalidFormatException e) {
    e.printStackTrace();
    return null;
  }
}
