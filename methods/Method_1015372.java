/** 
 * ????
 * @param name ??????????
 * @return
 */
@GetMapping("/applications/{name}") public ResultData application(@PathVariable("name") String name){
  Mono<ResultData> mono=this.toApplication(name,registry.getInstances(name).filter(Instance::isRegistered)).filter(a -> !a.getInstances().isEmpty()).map(r -> ResultData.ok(r).build()).defaultIfEmpty(ResultData.error(400).build());
  return mono.block();
}
