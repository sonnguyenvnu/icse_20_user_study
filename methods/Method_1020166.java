@GetMapping("/list") public BaseResponse list(){
  BaseResponse response=new BaseResponse();
  response.setSuccess(true);
  response.putData("areaList",this.areaService.listAll());
  return response;
}
