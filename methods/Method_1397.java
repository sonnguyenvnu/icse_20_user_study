/** 
 * Creates a new data source supplier with increasing-quality strategy with optional lazy state creation. <p>Note: for performance reasons the list doesn't get cloned, so the caller of this method should not modify the list once passed in here.
 * @param dataSourceSuppliers list of underlying suppliers
 * @param dataSourceLazy if true, the state of data source would be created only if necessary
 */
public static <T>IncreasingQualityDataSourceSupplier<T> create(List<Supplier<DataSource<T>>> dataSourceSuppliers,boolean dataSourceLazy){
  return new IncreasingQualityDataSourceSupplier<T>(dataSourceSuppliers,dataSourceLazy);
}
