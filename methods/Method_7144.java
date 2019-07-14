private DataSource getEncryptedFileDataSource(){
  if (encryptedFileDataSource == null) {
    encryptedFileDataSource=new EncryptedFileDataSource();
    addListenersToDataSource(encryptedFileDataSource);
  }
  return encryptedFileDataSource;
}
