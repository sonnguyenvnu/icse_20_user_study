/** 
 * @return HAVING conditoin0 AND condition1 OR condition2 ...
 */
@JSONField(serialize=false) public String getHavingString(boolean hasPrefix){
  String joinHaving="";
  if (joinList != null) {
    SQLConfig cfg;
    String c;
    boolean first=true;
    for (    Join j : joinList) {
      if (j.isAppJoin()) {
        continue;
      }
      cfg=j.isLeftOrRightJoin() ? j.getOutterConfig() : j.getJoinConfig();
      cfg.setAlias(cfg.getTable());
      c=((AbstractSQLConfig)cfg).getHavingString(false);
      if (StringUtil.isEmpty(c,true) == false) {
        joinHaving+=(first ? "" : ", ") + c;
        first=false;
      }
    }
  }
  having=StringUtil.getTrimedString(having);
  String[] keys=StringUtil.split(having,";");
  if (keys == null || keys.length <= 0) {
    return StringUtil.isEmpty(joinHaving,true) ? "" : (hasPrefix ? " HAVING " : "") + joinHaving;
  }
  String expression;
  String method;
  String suffix;
  for (int i=0; i < keys.length; i++) {
    expression=keys[i];
    int start=expression.indexOf("(");
    if (start < 0) {
      if (isPrepared() && PATTERN_HAVING.matcher(expression).matches() == false) {
        throw new UnsupportedOperationException("??? " + expression + " ????" + "?????? @having:\"column?value;function(arg0,arg1,...)?value...\"" + " ? column?value ????????? ^[A-Za-z0-9%!=<>]+$ ???????");
      }
      continue;
    }
    int end=expression.indexOf(")");
    if (start >= end) {
      throw new IllegalArgumentException("?? " + expression + " ????" + "@having:value ? value ?? SQL????? function(arg0,arg1,...) ?????");
    }
    method=expression.substring(0,start);
    if (StringUtil.isName(method) == false) {
      throw new IllegalArgumentException("?? " + method + " ????" + "?????? @having:\"column?value;function(arg0,arg1,...)?value...\"" + " ?SQL??? function ????????? ^[0-9a-zA-Z_]+$ ?");
    }
    suffix=expression.substring(end + 1,expression.length());
    if (isPrepared() && PATTERN_HAVING_SUFFIX.matcher((String)suffix).matches() == false) {
      throw new UnsupportedOperationException("??? " + suffix + " ????" + "?????? @having:\"column?value;function(arg0,arg1,...)?value...\"" + " ? ?value ????????? ^[0-9%!=<>]+$ ???????");
    }
    String[] ckeys=StringUtil.split(expression.substring(start + 1,end));
    if (ckeys != null) {
      for (int j=0; j < ckeys.length; j++) {
        if (isPrepared() && (StringUtil.isName(ckeys[j]) == false || ckeys[j].startsWith("_"))) {
          throw new IllegalArgumentException("?? " + ckeys[j] + " ????" + "?????? @having:\"column?value;function(arg0,arg1,...)?value...\"" + " ??? arg ????1??? _ ??????????????");
        }
        ckeys[j]=getKey(ckeys[j]);
      }
    }
    keys[i]=method + "(" + StringUtil.getString(ckeys) + ")" + suffix;
  }
  return (hasPrefix ? " HAVING " : "") + StringUtil.concat(StringUtil.getString(keys,AND),joinHaving,AND);
}
