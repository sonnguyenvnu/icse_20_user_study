/** 
 * Update the persisted value marking the current time as the persistent last modified time
 * @param key a key identifying the timestamp marker
 * @return the last modified item persisted
 */
@Override public ItemLastModified update(String key,String value){
  JpaItemLastModified lastModified=itemLastModifiedRepository.findOne(key);
  if (lastModified == null) {
    lastModified=new JpaItemLastModified(key);
  }
  lastModified.setValue(value);
  lastModified.setModifiedTime(DateTimeUtil.getNowUTCTime());
  return itemLastModifiedRepository.save(lastModified);
}
