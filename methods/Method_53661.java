@RequestMapping(value="/thanks/list",method=RequestMethod.GET) @ApiOperation(value="??????") @ResponseBody public DataTablesResult getThanksList(){
  DataTablesResult result=new DataTablesResult();
  List<Pay> list=new ArrayList<>();
  try {
    list=payService.getPayList(1);
  }
 catch (  Exception e) {
    result.setSuccess(false);
    result.setError("????????");
    return result;
  }
  result.setData(list);
  result.setSuccess(true);
  return result;
}
