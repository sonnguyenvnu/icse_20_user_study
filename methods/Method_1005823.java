/** 
 * @return
 * @throws MyException 
 */
@RequestMapping("/list.do") @ResponseBody @AuthPassport public JsonResult list(@ModelAttribute SourceQuery query) throws MyException {
  Project project=getProject(query);
  checkPermission(project,ProjectPermissionEnum.READ);
  Page page=new Page(query);
  page.setAllRow(sourceService.count(query));
  List<SourceDto> sourceDtos=SourceAdapter.getDto(sourceService.query(query));
  return new JsonResult().data(sourceDtos).page(page);
}
