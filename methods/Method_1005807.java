/** 
 * bug??
 * @return
 * @throws MyException
 */
@RequestMapping("/list.do") @ResponseBody @AuthPassport public JsonResult list(@ModelAttribute BugLogQuery query) throws Exception {
  if (query.getBugId() == null) {
    return new JsonResult().data(Lists.newArrayList()).page(new Page(query));
  }
  Project project=getProject(query);
  checkPermission(project,ProjectPermissionEnum.READ);
  query.setPageSize(100);
  query.setSort(TableField.SORT.CREATE_TIME_DES);
  List<BugLogPO> bugLogPOList=bugLogService.select(query);
  Page page=new Page(query);
  page.setAllRow(bugLogService.count(query));
  List<BugLogDTO> dtoList=BugLogAdapter.getDto(bugLogPOList);
  return new JsonResult().data(dtoList).page(page);
}
