@RequestMapping("/test") public ViewEntity test(@RequestBody CatRO ro){
{
    Map<String,Object> catMap=new HashMap<>();
    catMap.put("id","");
    Map<String,Object> dogMap=new HashMap<>();
    dogMap.put("number","");
    dogMap.put("userName","");
    ro.getResultKeyMap().put("catTest",catMap);
    ro.getResultKeyMap().put("dogTest",dogMap);
  }
  String[] resultKeys={"catTest.id","catTest.catFriendName","dogTest.userName"};
  ro.setResultKeys(resultKeys);
  List<Object> inList=new ArrayList<>();
  inList.add("gggg");
  inList.add("xxxxx");
  ro.setOrderBy("catTest.catFriendName,catTest.id");
  Sort sort1=new Sort();
  sort1.setOrderBy("catTest.catFriendName");
  sort1.setDirection(Direction.ASC);
  Sort sort2=new Sort();
  sort2.setOrderBy("catTest.id");
  sort2.setDirection(Direction.DESC);
  List<Sort> sortList=new ArrayList<Sort>();
  sortList.add(sort1);
  sortList.add(sort2);
  ro.setOrderBy("catTest.catFriendName,catTest.id");
  ro.setDirection(Direction.DESC);
  CriteriaBuilder.ResultMappedBuilder builder=CriteriaBuilder.buildResultMapped(CatTest.class,ro);
  builder.and().in("catTest.catFriendName",inList);
  String sourceScript="catTest LEFT JOIN dogTest on catTest.dogId = dogTest.id";
  Criteria.ResultMappedCriteria resultMapped=builder.get();
  resultMapped.setSourceScript(sourceScript);
  Page<Map<String,Object>> page=repository.find(resultMapped);
  return ViewEntity.ok(page);
}
