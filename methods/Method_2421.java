@ApiOperation(value="????") @RequiresPermissions("upms:session:read") @RequestMapping(value="/list",method=RequestMethod.GET) @ResponseBody public Object list(@RequestParam(required=false,defaultValue="0",value="offset") int offset,@RequestParam(required=false,defaultValue="10",value="limit") int limit){
  return sessionDAO.getActiveSessions(offset,limit);
}
