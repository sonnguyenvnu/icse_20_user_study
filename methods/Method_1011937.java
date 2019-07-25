/** 
 * Saves the model in the factory-specific format (including conversion when needed).
 */
@Override public void save(@NotNull SModel model,@NotNull DataSource dataSource) throws ModelSaveException, IOException {
  if (!(supports(dataSource))) {
    throw new UnsupportedDataSourceException(dataSource);
  }
  SModelSimpleHeader newHeader=new SModelSimpleHeader(model.getReference());
  jetbrains.mps.smodel.SModel modelData=((SModelBase)model).getSModel();
  XmlModelPersistence.XmlCustomPersistenceLoadFacility auxFacility=new XmlModelPersistence.XmlCustomPersistenceLoadFacility((StreamDataSource)dataSource,this);
  auxFacility.writeModel(newHeader,modelData);
}
