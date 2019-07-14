public void replaceSupplier(Supplier<DataSource<T>> supplier){
  mCurrentDataSourceSupplier=supplier;
  for (  RetainingDataSource dataSource : mDataSources) {
    if (!dataSource.isClosed()) {
      dataSource.setSupplier(supplier);
    }
  }
}
