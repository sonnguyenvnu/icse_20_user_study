@RequestMapping(method=RequestMethod.GET) @ResponseBody public ResponseMessage<List<TSFillRuleEntity>> list(){
  List<TSFillRuleEntity> listTSFillRules=tSFillRuleService.getList(TSFillRuleEntity.class);
  return Result.success(listTSFillRules);
}
