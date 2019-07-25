@Override public boolean supports(@NotNull DataSource source){
  return source instanceof FileSystemBasedDataSource && source instanceof StreamDataSource;
}
