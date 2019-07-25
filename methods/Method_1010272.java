public static FileWithBackupDataSource create(FileDataSource source){
  return new FileWithBackupDataSource(source.getFile());
}
