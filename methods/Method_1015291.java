/** 
 * ??
 * @param id id
 * @return model
 * @throws UserNotFoundException UserNotFoundException
 */
@RequestMapping(value="forbid/{id}",method=RequestMethod.GET) @ApiOperation(value="??",notes="?????id?????????",httpMethod="GET",produces=MediaType.APPLICATION_JSON_UTF8_VALUE) @ApiImplicitParams({@ApiImplicitParam(name="id",value="??????id",required=true,dataType="Long",paramType="path")}) @ApiResponses(value={@ApiResponse(code=404,message="Not Found"),@ApiResponse(code=400,message="No Name Provided")}) public Result forbid(@PathVariable("id") Long id) throws UserNotFoundException {
  AdminModel model=service.findAdminUserById(id);
  if (model == null) {
    return new Result(CodeConst.NULL_DATA.getResultCode(),CodeConst.NULL_DATA.getMessage());
  }
  model=service.forbidAdminUserById(id);
  return new Result<>(model);
}
