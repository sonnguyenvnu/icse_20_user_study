/** 
 * {@inheritDoc}
 */
@Override public List<DataPoint> findByAccountName(String accountName){
  Assert.hasLength(accountName);
  return repository.findByIdAccount(accountName);
}
