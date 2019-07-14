/** 
 * ??ID???? api :localhost:8099/users/2
 * @param id
 * @return
 */
@RequestMapping(value="/{id}",method=RequestMethod.DELETE) @ResponseBody @ApiOperation(value="??id??????",notes="??????1 ?? 0 ??") public ResponseEntity<Object> deleteUser(@PathVariable Integer id){
  return new ResponseEntity<>(userService.removeUser(id.longValue()),HttpStatus.OK);
}
