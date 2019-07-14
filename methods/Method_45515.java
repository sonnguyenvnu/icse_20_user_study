/** 
 * Sets uniqueId.
 * @param uniqueId the uniqueId
 * @return this unique id
 */
public S setUniqueId(String uniqueId){
  checkNormalWithCommaColon("uniqueId",uniqueId);
  this.uniqueId=uniqueId;
  return castThis();
}
