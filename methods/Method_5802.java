private void maybeAddListenerToDataSource(@Nullable DataSource dataSource,TransferListener listener){
  if (dataSource != null) {
    dataSource.addTransferListener(listener);
  }
}
