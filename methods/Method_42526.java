/** 
 * ????????????
 */
public PmsOperator findOperatorByLoginName(String loginName){
  return pmsOperatorDao.findByLoginName(loginName);
}
