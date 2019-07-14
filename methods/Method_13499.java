@GlobalTransactional(timeoutMills=300000,name="spring-cloud-demo-tx") @RequestMapping(value="/fescar/rest",method=RequestMethod.GET,produces="application/json") public String rest(){
  String result=restTemplate.getForObject("http://127.0.0.1:18082/storage/" + COMMODITY_CODE + "/" + ORDER_COUNT,String.class);
  if (!SUCCESS.equals(result)) {
    throw new RuntimeException();
  }
  String url="http://127.0.0.1:18083/order";
  HttpHeaders headers=new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
  MultiValueMap<String,String> map=new LinkedMultiValueMap<String,String>();
  map.add("userId",USER_ID);
  map.add("commodityCode",COMMODITY_CODE);
  map.add("orderCount",ORDER_COUNT + "");
  HttpEntity<MultiValueMap<String,String>> request=new HttpEntity<MultiValueMap<String,String>>(map,headers);
  ResponseEntity<String> response=restTemplate.postForEntity(url,request,String.class);
  result=response.getBody();
  if (!SUCCESS.equals(result)) {
    throw new RuntimeException();
  }
  return SUCCESS;
}
