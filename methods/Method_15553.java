public String getJoinString() throws Exception {
  String joinOns="";
  if (joinList != null) {
    String quote=getQuote();
    List<Object> pvl=new ArrayList<>();
    boolean changed=false;
    String sql=null;
    SQLConfig jc;
    String jt;
    String tn;
    for (    Join j : joinList) {
      if (j.isAppJoin()) {
        continue;
      }
      jc=j.getJoinConfig();
      jc.setPrepared(isPrepared());
      jt=jc.getTable();
      tn=j.getTargetName();
switch (j.getJoinType()) {
case "<":
case ">":
        jc.setMain(true).setKeyPrefix(false);
      sql=(">".equals(j.getJoinType()) ? " RIGHT" : " LEFT") + " JOIN ( " + jc.getSQL(isPrepared()) + " ) AS " + quote + jt + quote + " ON " + quote + jt + quote + "." + quote + j.getKey() + quote + " = " + quote + tn + quote + "." + quote + j.getTargetKey() + quote;
    jc.setMain(false).setKeyPrefix(true);
  pvl.addAll(jc.getPreparedValueList());
changed=true;
break;
case "":
case "|":
case "&":
case "!":
case "^":
sql=("*".equals(j.getJoinType()) ? " CROSS JOIN " : " INNER JOIN ") + jc.getTablePath() + " ON " + quote + jt + quote + "." + quote + j.getKey() + quote + " = " + quote + tn + quote + "." + quote + j.getTargetKey() + quote;
break;
default :
throw new UnsupportedOperationException("join:value ? value ?? " + j.getJoinType() + "/" + j.getPath() + "?????? " + j.getJoinType() + " ? [@ APP, < LEFT, > RIGHT, | FULL, & INNER, ! OUTTER, ^ SIDE, * CROSS] ???JOIN?? !");
}
joinOns+="  \n  " + sql;
}
if (changed) {
pvl.addAll(preparedValueList);
preparedValueList=pvl;
}
}
return joinOns;
}
