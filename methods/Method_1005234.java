@RequestMapping(value="/{id}",method=RequestMethod.GET) @ResponseBody public ResponseMessage<?> get(@PathVariable("id") String id){
  TSFillRuleEntity task=tSFillRuleService.get(TSFillRuleEntity.class,id);
  if (task == null) {
    return Result.error("??ID???????????");
  }
  return Result.success(task);
}
