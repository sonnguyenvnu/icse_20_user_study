/** 
 * ???????Table? ??  {@link #TABLE_KEY_MAP} ??
 * @return
 */
@JSONField(serialize=false) @Override public String getSQLTable(){
  return TABLE_KEY_MAP.containsKey(table) ? TABLE_KEY_MAP.get(table) : table;
}
