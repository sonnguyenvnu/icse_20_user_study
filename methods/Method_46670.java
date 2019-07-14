/** 
 * ??????
 * @param page      ??
 * @param limit     ???
 * @param extState  extState
 * @param registrar registrar
 * @return ExceptionList
 */
@GetMapping({"/exceptions/{page}","/exceptions","/exceptions/{page}/{limit}"}) public ExceptionList exceptionList(@RequestParam(value="page",required=false) @PathVariable(value="page",required=false) Integer page,@RequestParam(value="limit",required=false) @PathVariable(value="limit",required=false) Integer limit,@RequestParam(value="extState",required=false) Integer extState,@RequestParam(value="registrar",required=false) Integer registrar){
  return txExceptionService.exceptionList(page,limit,extState,null,registrar);
}
