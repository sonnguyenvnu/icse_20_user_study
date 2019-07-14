/** 
 * ???????????????.
 * @param loginName .
 * @return operator .
 */
public PmsOperator findByLoginName(String loginName){
  return super.getSessionTemplate().selectOne(getStatement("findByLoginName"),loginName);
}
