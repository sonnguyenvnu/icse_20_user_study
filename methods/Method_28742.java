/** 
 * Increment the number stored at field in the hash at key by a double precision floating point value. If key does not exist, a new key holding a hash is created. If field does not exist or holds a string, the value is set to 0 before applying the operation. Since the value argument is signed you can use this command to perform both increments and decrements. <p> The range of values supported by HINCRBYFLOAT is limited to double precision floating point values. <p> <b>Time complexity:</b> O(1)
 * @param key
 * @param field
 * @param value
 * @return Double precision floating point reply The new value at field after the incrementoperation.
 */
@Override public Double hincrByFloat(final String key,final String field,final double value){
  checkIsInMultiOrPipeline();
  client.hincrByFloat(key,field,value);
  final String dval=client.getBulkReply();
  return (dval != null ? new Double(dval) : null);
}
