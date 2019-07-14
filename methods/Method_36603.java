@RequestMapping("/insert/{author}/{title}") public Map<String,Object> insert(@PathVariable("author") String author,@PathVariable("title") String title){
  Map<String,Object> resultMap=new HashMap<String,Object>();
  try {
    newWriteService.addNews(author,title);
    resultMap.put("success",true);
  }
 catch (  Throwable throwable) {
    resultMap.put("success",false);
    resultMap.put("error",throwable.getMessage());
  }
  return resultMap;
}
