/** 
 * ?????Event????
 * @return
 */
@GetMapping(path="/instances/events") public ResultData events(@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum,@RequestParam(value="pageSize",required=false,defaultValue="8") int pageSize){
  List<InstanceEvent> list=ReactorUtils.optional(eventStore.findAll()).map(r -> r.stream()).get().collect(Collectors.toList());
  PageResult<InstanceEvent> pageResult=PagingUtils.pageBuider(list,pageNum,pageSize).build();
  return ResultData.ok(pageResult).build();
}
