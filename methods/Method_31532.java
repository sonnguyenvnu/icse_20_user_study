/** 
 * Since Sybase ASE does not support schema, dropping out the schema name for toString method
 * @see SchemaObject#toString()
 */
@Override public String toString(){
  return name;
}
