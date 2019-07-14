/** 
 * ????pmsOperator
 * @param pageParam
 * @param ActivityVo PmsOperator
 * @return
 */
public PageBean listPage(PageParam pageParam,PmsOperator pmsOperator){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("loginName",pmsOperator.getLoginName());
  paramMap.put("realName",pmsOperator.getRealName());
  paramMap.put("status",pmsOperator.getStatus());
  return pmsOperatorDao.listPage(pageParam,paramMap);
}
