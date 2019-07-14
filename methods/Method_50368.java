/** 
 * build  PageParameter.
 * @param pageParameter pageParameter
 * @param totalCount    totalCount
 * @return {@linkplain PageParameter}
 */
public static PageParameter buildPage(final PageParameter pageParameter,final int totalCount){
  final int currentPage=pageParameter.getCurrentPage();
  pageParameter.setTotalCount(totalCount);
  int totalPage=totalCount / pageParameter.getPageSize() + ((totalCount % pageParameter.getPageSize() == 0) ? 0 : 1);
  pageParameter.setTotalPage(totalPage);
  pageParameter.setPrePage(currentPage - 1);
  pageParameter.setNextPage(currentPage + 1);
  return pageParameter;
}
