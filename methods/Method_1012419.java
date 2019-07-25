/** 
 * ????(??)
 */
@GetMapping("/list") public R<IPage<LogApiVo>> list(@ApiIgnore @RequestParam Map<String,Object> log,Query query){
  IPage<LogApi> pages=logService.page(Condition.getPage(query.setDescs("create_time")),Condition.getQueryWrapper(log,LogApi.class));
  List<LogApiVo> records=pages.getRecords().stream().map(logApi -> {
    LogApiVo vo=BeanUtil.copy(logApi,LogApiVo.class);
    vo.setStrId(Func.toStr(logApi.getId()));
    return vo;
  }
).collect(Collectors.toList());
  IPage<LogApiVo> pageVo=new Page<>(pages.getCurrent(),pages.getSize(),pages.getTotal());
  pageVo.setRecords(records);
  return R.data(pageVo);
}
