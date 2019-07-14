private void addListenersToDataSource(DataSource dataSource){
  for (int i=0; i < transferListeners.size(); i++) {
    dataSource.addTransferListener(transferListeners.get(i));
  }
}
