/** 
 * ???????????,??????id
 * @return ??id
 */
default List<String> allTarget(){
  return stream().map(Relation::getTarget).collect(Collectors.toList());
}