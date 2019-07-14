/** 
 * ??WHERE
 * @param method
 * @param where
 * @return
 * @throws Exception 
 */
@JSONField(serialize=false) public String getWhereString(boolean hasPrefix,RequestMethod method,Map<String,Object> where,Map<String,List<String>> combine,List<Join> joinList,boolean verifyName) throws Exception {
  Set<Entry<String,List<String>>> combineSet=combine == null ? null : combine.entrySet();
  if (combineSet == null || combineSet.isEmpty()) {
    Log.w(TAG,"getWhereString  combineSet == null || combineSet.isEmpty() >> return \"\";");
    return "";
  }
  List<String> keyList;
  String whereString="";
  boolean isCombineFirst=true;
  int logic;
  boolean isItemFirst;
  String c;
  String cs;
  for (  Entry<String,List<String>> ce : combineSet) {
    keyList=ce == null ? null : ce.getValue();
    if (keyList == null || keyList.isEmpty()) {
      continue;
    }
    if ("|".equals(ce.getKey())) {
      logic=Logic.TYPE_OR;
    }
 else     if ("!".equals(ce.getKey())) {
      logic=Logic.TYPE_NOT;
    }
 else {
      logic=Logic.TYPE_AND;
    }
    isItemFirst=true;
    cs="";
    for (    String key : keyList) {
      c=getWhereItem(key,where.get(key),method,verifyName);
      if (StringUtil.isEmpty(c,true)) {
        continue;
      }
      cs+=(isItemFirst ? "" : (Logic.isAnd(logic) ? AND : OR)) + "(" + c + ")";
      isItemFirst=false;
    }
    whereString+=(isCombineFirst ? "" : AND) + (Logic.isNot(logic) ? NOT : "") + " (  " + cs + "  ) ";
    isCombineFirst=false;
  }
  if (joinList != null) {
    String newWs="";
    String ws="" + whereString;
    List<Object> newPvl=new ArrayList<>();
    List<Object> pvl=new ArrayList<>(preparedValueList);
    SQLConfig jc;
    String js;
    boolean changed=false;
    for (    Join j : joinList) {
switch (j.getJoinType()) {
case "@":
case "<":
case ">":
        break;
case "":
case "|":
case "&":
case "!":
case "^":
case "*":
      jc=j.getJoinConfig();
    boolean isMain=jc.isMain();
  jc.setMain(false).setPrepared(isPrepared()).setPreparedValueList(new ArrayList<Object>());
js=jc.getWhereString(false);
jc.setMain(isMain);
if (StringUtil.isEmpty(js,true)) {
continue;
}
if (StringUtil.isEmpty(newWs,true) == false) {
newWs+=AND;
}
if ("^".equals(j.getJoinType())) {
newWs+=" (   ( " + ws + (StringUtil.isEmpty(ws,true) ? "" : AND + NOT) + " ( " + js + " ) ) " + OR + " ( " + js + AND + NOT + " ( " + ws + " )  )   ) ";
newPvl.addAll(pvl);
newPvl.addAll(jc.getPreparedValueList());
newPvl.addAll(jc.getPreparedValueList());
newPvl.addAll(pvl);
}
 else {
logic=Logic.getType(j.getJoinType());
newWs+=" ( " + getCondition(Logic.isNot(logic),ws + (StringUtil.isEmpty(ws,true) ? "" : (Logic.isAnd(logic) ? AND : OR)) + " ( " + js + " ) ") + " ) ";
newPvl.addAll(pvl);
newPvl.addAll(jc.getPreparedValueList());
}
changed=true;
break;
default :
throw new UnsupportedOperationException("join:value ? value ?? " + j.getJoinType() + "/" + j.getPath() + "?????? " + j.getJoinType() + " ? [@ APP, < LEFT, > RIGHT, | FULL, & INNER, ! OUTTER, ^ SIDE, * CROSS] ???JOIN?? !");
}
}
if (changed) {
whereString=newWs;
preparedValueList=newPvl;
}
}
String s=whereString.isEmpty() ? "" : (hasPrefix ? " WHERE " : "") + whereString;
if (s.isEmpty() && RequestMethod.isQueryMethod(method) == false) {
throw new UnsupportedOperationException("?????????????");
}
return s;
}
