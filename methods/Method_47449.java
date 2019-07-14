/** 
 * api :localhost:8099/users?id=99 http://localhost:8099/users?limit=2&page=2
 * @param request
 * @return
 */
@RequestMapping(method=RequestMethod.GET,produces="application/json;charset=UTF-8") @ResponseBody public ResponseEntity<Object> list(HttpServletRequest request){
  Map<String,Object> map=CommonUtil.getParameterMap(request);
  return new ResponseEntity<>(userService.getList(map),HttpStatus.OK);
}
