/** 
 * @return ????????
 */
default List<Relation> all(){
  return stream().collect(Collectors.toList());
}
