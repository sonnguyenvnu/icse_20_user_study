/** 
 * ??SET
 * @return
 * @throws Exception 
 */
@JSONField(serialize=false) public String getSetString() throws Exception {
  return getSetString(getMethod(),getContent(),!isTest());
}
