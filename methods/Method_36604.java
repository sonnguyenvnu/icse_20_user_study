@RequestMapping("/query/{author}") public Map<String,Object> query(@PathVariable("author") String author){
  Map<String,Object> resultMap=new HashMap<String,Object>();
  try {
    List<NewsDO> ret=newReadService.read(author);
    resultMap.put("success",true);
    resultMap.put("count",ret.size());
    int i=0;
    for (    NewsDO newDO : ret) {
      resultMap.put(String.valueOf(++i),newDO.getAuthor() + "-" + newDO.getTitle());
    }
  }
 catch (  Throwable throwable) {
    resultMap.put("success",false);
    resultMap.put("error",throwable.getMessage());
  }
  return resultMap;
}
