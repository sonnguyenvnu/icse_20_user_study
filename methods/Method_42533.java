/** 
 * ????pmsOperator
 * @param pageParam
 * @param ActivityVo PmsOperator
 * @return
 */
public PageBean listPage(PageParam pageParam,PmsRolePermission pmsRolePermission){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  return pmsRolePermissionDao.listPage(pageParam,paramMap);
}
