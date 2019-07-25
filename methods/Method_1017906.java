/** 
 * Delete an item from last modified table
 * @param key a key identifying the timestamp marker
 */
@Override public void delete(String key){
  JpaItemLastModified lastModified=itemLastModifiedRepository.findOne(key);
  if (lastModified != null) {
    itemLastModifiedRepository.delete(lastModified);
  }
}
