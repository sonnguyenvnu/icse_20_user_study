@RequestMapping(value="/pay/check/list",method=RequestMethod.GET) @ApiOperation(value="????????") @ResponseBody public DataTablesResult getCheckList(){
  DataTablesResult result=new DataTablesResult();
  List<Pay> list=new ArrayList<>();
  try {
    list=payService.getPayList(0);
  }
 catch (  Exception e) {
    result.setSuccess(false);
    result.setError("??????????");
    return result;
  }
  result.setData(list);
  result.setSuccess(true);
  return result;
}
