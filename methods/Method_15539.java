/** 
 * ??WHERE
 * @return
 * @throws Exception 
 */
@JSONField(serialize=false) @Override public String getWhereString(boolean hasPrefix) throws Exception {
  return getWhereString(hasPrefix,getMethod(),getWhere(),getCombine(),getJoinList(),!isTest());
}
