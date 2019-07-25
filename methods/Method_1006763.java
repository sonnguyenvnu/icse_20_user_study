@RequestMapping("/domain") public ViewEntity domain(){
  List<Long> catIdList=new ArrayList<>();
  catIdList.add(2L);
  catIdList.add(3L);
  CriteriaBuilder.DomainObjectBuilder builder=CriteriaBuilder.buildDomainObject(Cat.class,Mouse.class);
  builder.and().in("id",catIdList);
  builder.domain().relative(CatMouse.class).on("catId").with("mouseId");
  Criteria.DomainObjectCriteria criteria=builder.get();
  List<DomainObject<Cat,Mouse>> list=this.catRepository.listDomainObject(criteria);
  String str=JsonX.toJson(list);
  List<DomainObject> test=JsonX.toList(str,DomainObject.class);
  System.out.println(test);
  return ViewEntity.ok(list);
}
