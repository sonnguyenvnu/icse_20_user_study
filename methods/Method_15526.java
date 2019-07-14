@JSONField(serialize=false) public String getGroupString(boolean hasPrefix){
  String joinGroup="";
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
      c=((AbstractSQLConfig)cfg).getGroupString(false);
      if (StringUtil.isEmpty(c,true) == false) {
        joinGroup+=(first ? "" : ", ") + c;
        first=false;
      }
    }
  }
  group=StringUtil.getTrimedString(group);
  String[] keys=StringUtil.split(group);
  if (keys == null || keys.length <= 0) {
    return StringUtil.isEmpty(joinGroup,true) ? "" : (hasPrefix ? " GROUP BY " : "") + joinGroup;
  }
  for (int i=0; i < keys.length; i++) {
    if (isPrepared()) {
      if (StringUtil.isName(keys[i]) == false) {
        throw new IllegalArgumentException("@group:value ? value??? , ??????????1????????????");
      }
    }
    keys[i]=getKey(keys[i]);
  }
  return (hasPrefix ? " GROUP BY " : "") + StringUtil.concat(StringUtil.getString(keys),joinGroup,", ");
}
