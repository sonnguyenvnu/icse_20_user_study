private void init(Supplier<DataSource<CloseableReference<CloseableImage>>> dataSourceSupplier){
  mDataSourceSupplier=dataSourceSupplier;
  maybeUpdateDebugOverlay(null);
}
