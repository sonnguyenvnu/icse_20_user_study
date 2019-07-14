/** 
 * ???????????
 */
public PageBean queryAccountHistoryListPageByRole(PageParam pageParam,Map<String,Object> params){
  String accountType=(String)params.get("accountType");
  if (StringUtils.isBlank(accountType)) {
    throw AccountBizException.ACCOUNT_TYPE_IS_NULL;
  }
  return rpAccountDao.listPage(pageParam,params);
}
