/** 
 * Set the specified hash field to the specified value if the field not exists. <b>Time complexity:</b> O(1)
 * @param key
 * @param field
 * @param value
 * @return If the field already exists, 0 is returned, otherwise if a new field is created 1 isreturned.
 */
@Override public Long hsetnx(final String key,final String field,final String value){
  checkIsInMultiOrPipeline();
  client.hsetnx(key,field,value);
  return client.getIntegerReply();
}
