public Page<User> getUserByCondition(int size,int page,User user){
  Query query=new Query();
  Criteria criteria=new Criteria();
  if (!StringUtils.isEmpty(user.getName())) {
    criteria.and("name").is(user.getName());
  }
  if (!StringUtils.isEmpty(user.getDescription())) {
    criteria.and("description").regex(user.getDescription());
  }
  query.addCriteria(criteria);
  Sort sort=new Sort(Sort.Direction.DESC,"age");
  Pageable pageable=PageRequest.of(page,size,sort);
  List<User> users=template.find(query.with(pageable),User.class);
  return PageableExecutionUtils.getPage(users,pageable,() -> template.count(query,User.class));
}
