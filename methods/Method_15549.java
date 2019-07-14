/** 
 * WHERE key contains childs
 * @param key
 * @param childs null ? "" : (empty ? no child : contains childs)
 * @param type |, &, !
 * @return LOGIC [  ( key LIKE '[" + childs[i] + "]'  OR  key LIKE '[" + childs[i] + ", %'OR  key LIKE '%, " + childs[i] + ", %'  OR  key LIKE '%, " + childs[i] + "]' )  ]
 * @throws IllegalArgumentException 
 */
@JSONField(serialize=false) public String getContainString(String key,Object[] childs,int type) throws IllegalArgumentException {
  boolean not=Logic.isNot(type);
  String condition="";
  if (childs != null) {
    for (int i=0; i < childs.length; i++) {
      if (childs[i] != null) {
        if (childs[i] instanceof JSON) {
          throw new IllegalArgumentException(key + "<>:value ?value?????JSON?");
        }
        if (DATABASE_POSTGRESQL.equals(getDatabase())) {
          condition+=(i <= 0 ? "" : (Logic.isAnd(type) ? AND : OR)) + getKey(key) + " @> " + getValue(newJSONArray(childs[i]));
        }
 else {
          condition+=(i <= 0 ? "" : (Logic.isAnd(type) ? AND : OR)) + "json_contains(" + getKey(key) + ", " + getValue(childs[i].toString()) + ")";
        }
      }
    }
    if (condition.isEmpty()) {
      condition=(getKey(key) + SQL.isNull(true) + OR + getLikeString(key,"[]"));
    }
 else {
      condition=(getKey(key) + SQL.isNull(false) + AND + "(" + condition + ")");
    }
  }
  if (condition.isEmpty()) {
    return "";
  }
  return getCondition(not,condition);
}
