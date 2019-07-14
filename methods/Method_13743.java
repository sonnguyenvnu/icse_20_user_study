/** 
 * {@inheritDoc}
 */
@Override public void saveChanges(String name,Account update){
  Account account=repository.findByName(name);
  Assert.notNull(account,"can't find account with name " + name);
  account.setIncomes(update.getIncomes());
  account.setExpenses(update.getExpenses());
  account.setSaving(update.getSaving());
  account.setNote(update.getNote());
  account.setLastSeen(new Date());
  repository.save(account);
  log.debug("account {} changes has been saved",name);
  statisticsClient.updateStatistics(name,account);
}
