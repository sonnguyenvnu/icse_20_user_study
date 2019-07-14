/** 
 * use  {@link Relation.Direction#POSITIVE}
 * @see this#has(String, String, String, Relation.Direction)
 */
default boolean has(String relation){
  return !findAll(relation).isEmpty();
}
