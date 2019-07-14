/** 
 * ???????????????
 * @param pageParam
 * @param params ?map?key? ?accountNo...??????
 * @return
 * @throws BizException
 */
public PageBean querySettDailyCollectListPage(PageParam pageParam,Map<String,Object> params) throws BizException {
  return settDailyCollectMapper.listPage(pageParam,params);
}
