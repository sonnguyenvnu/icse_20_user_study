/** 
 * ????(??)
 */
@GetMapping("/list") public R<IPage<LogUsualVo>> list(@ApiIgnore @RequestParam Map<String,Object> log,Query query){
  IPage<LogUsual> pages=logService.page(Condition.getPage(query),Condition.getQueryWrapper(log,LogUsual.class));
  List<LogUsualVo> records=pages.getRecords().stream().map(logApi -> {
    LogUsualVo vo=BeanUtil.copy(logApi,LogUsualVo.class);
    vo.setStrId(Func.toStr(logApi.getId()));
    return vo;
  }
).collect(Collectors.toList());
  IPage<LogUsualVo> pageVo=new Page<>(pages.getCurrent(),pages.getSize(),pages.getTotal());
  pageVo.setRecords(records);
  return R.data(pageVo);
}
