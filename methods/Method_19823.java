/** 
 * ?? count??? getUserByCondition??
 */
public Mono<Long> getUserByConditionCount(User user){
  Query query=getQuery(user);
  return template.count(query,User.class);
}
