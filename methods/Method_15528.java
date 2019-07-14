@JSONField(serialize=false) public String getOrderString(boolean hasPrefix){
  String joinOrder="";
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
      c=((AbstractSQLConfig)cfg).getOrderString(false);
      if (StringUtil.isEmpty(c,true) == false) {
        joinOrder+=(first ? "" : ", ") + c;
        first=false;
      }
    }
  }
  order=StringUtil.getTrimedString(order);
  if (order.contains("+")) {
    order=order.replaceAll("\\+"," ASC ");
  }
  if (order.contains("-")) {
    order=order.replaceAll("-"," DESC ");
  }
  String[] keys=StringUtil.split(order);
  if (keys == null || keys.length <= 0) {
    return StringUtil.isEmpty(joinOrder,true) ? "" : (hasPrefix ? " ORDER BY " : "") + joinOrder;
  }
  String origin;
  String sort;
  int index;
  for (int i=0; i < keys.length; i++) {
    index=keys[i].trim().endsWith(" ASC") ? keys[i].lastIndexOf(" ASC") : -1;
    if (index < 0) {
      index=keys[i].trim().endsWith(" DESC") ? keys[i].lastIndexOf(" DESC") : -1;
      sort=index <= 0 ? "" : " DESC ";
    }
 else {
      sort=" ASC ";
    }
    origin=index < 0 ? keys[i] : keys[i].substring(0,index);
    if (isPrepared()) {
      if (StringUtil.isName(origin) == false) {
        throw new IllegalArgumentException("?????? @order:value ? value??? , ??????" + " column+ / column- ? column???1???????????????");
      }
    }
    keys[i]=getKey(origin) + sort;
  }
  return (hasPrefix ? " ORDER BY " : "") + StringUtil.concat(StringUtil.getString(keys),joinOrder,", ");
}
