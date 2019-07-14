/** 
 * @return The quoted name of the schema.
 */
@Override public String toString(){
  return database.quote(name);
}
