/** 
 * ????pmsOperator
 * @param pageParam
 * @param ActivityVo PmsOperator
 * @return
 */
public PageBean listPage(PageParam pageParam,PmsRole pmsRole){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("roleName",pmsRole.getRoleName());
  return pmsRoleDao.listPage(pageParam,paramMap);
}
