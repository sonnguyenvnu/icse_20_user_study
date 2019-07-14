/** 
 * ????pmsOperator
 * @param pageParam
 * @param ActivityVo PmsOperator
 * @return
 */
public PageBean listPage(PageParam pageParam,PmsPermission pmsPermission){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("permissionName",pmsPermission.getPermissionName());
  paramMap.put("permission",pmsPermission.getPermission());
  return pmsPermissionDao.listPage(pageParam,paramMap);
}
