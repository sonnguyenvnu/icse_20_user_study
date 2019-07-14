@JSONField(serialize=false) public String getColumnString(boolean inSQLJoin) throws Exception {
switch (getMethod()) {
case HEAD:
case HEADS:
    if (isPrepared() && column != null) {
      String origin;
      String alias;
      int index;
      for (      String c : column) {
        index=c.lastIndexOf(":");
        origin=index < 0 ? c : c.substring(0,index);
        alias=index < 0 ? null : c.substring(index + 1);
        if (StringUtil.isName(origin) == false || (alias != null && StringUtil.isName(alias) == false)) {
          throw new IllegalArgumentException("HEAD??: ?????? @column:value ? value??? , ??????" + " column:alias ? column ???1???????alias??alias????1???????????????");
        }
      }
    }
  return SQL.count(column != null && column.size() == 1 ? getKey(Pair.parseEntry(column.get(0),true).getKey()) : "*");
case POST:
if (column == null || column.isEmpty()) {
  throw new IllegalArgumentException("POST ?????Table??????? key:value ?");
}
String s="";
boolean pfirst=true;
for (String c : column) {
if (isPrepared() && StringUtil.isName(c) == false) {
throw new IllegalArgumentException("POST??: ??? key:value ??key????1????");
}
s+=((pfirst ? "" : ",") + getKey(c));
pfirst=false;
}
return "(" + s + ")";
case GET:
case GETS:
boolean isQuery=RequestMethod.isQueryMethod(method);
String joinColumn="";
if (isQuery && joinList != null) {
SQLConfig ecfg;
SQLConfig cfg;
String c;
boolean first=true;
for (Join j : joinList) {
if (j.isAppJoin()) {
continue;
}
ecfg=j.getOutterConfig();
if (ecfg != null && ecfg.getColumn() != null) {
cfg=ecfg;
}
 else {
cfg=j.getJoinConfig();
}
cfg.setAlias(cfg.getTable());
c=((AbstractSQLConfig)cfg).getColumnString(true);
if (StringUtil.isEmpty(c,true) == false) {
joinColumn+=(first ? "" : ", ") + c;
first=false;
}
inSQLJoin=true;
}
}
String tableAlias=getAlias();
String[] keys=column == null ? null : column.toArray(new String[]{});
if (keys == null || keys.length <= 0) {
boolean noColumn=column != null && inSQLJoin;
String mc=isKeyPrefix() == false ? (noColumn ? "" : "*") : (noColumn ? "" : tableAlias + ".*");
return StringUtil.concat(mc,joinColumn,", ",true);
}
String expression;
String method=null;
for (int i=0; i < keys.length; i++) {
expression=keys[i];
int start=expression.indexOf("(");
int end=0;
if (start >= 0) {
end=expression.indexOf(")");
if (start >= end) {
throw new IllegalArgumentException("?? " + expression + " ????" + "@having:value ? value ?? SQL????? function(arg0,arg1,...) ?????");
}
method=expression.substring(0,start);
if (StringUtil.isName(method) == false) {
throw new IllegalArgumentException("?? " + method + " ????" + "?????? @column:\"column0,column1:alias;function0(arg0,arg1,...);function1(...):alias...\"" + " ?SQL??? function ????????? ^[0-9a-zA-Z_]+$ ?");
}
}
boolean isColumn=start < 0;
String[] ckeys=StringUtil.split(isColumn ? expression : expression.substring(start + 1,end));
String quote=getQuote();
if (ckeys != null && ckeys.length > 0) {
String origin;
String alias;
int index;
for (int j=0; j < ckeys.length; j++) {
index=ckeys[j].lastIndexOf(":");
origin=index < 0 ? ckeys[j] : ckeys[j].substring(0,index);
alias=index < 0 ? null : ckeys[j].substring(index + 1);
if (isPrepared()) {
if (isColumn) {
if (StringUtil.isName(origin) == false || (alias != null && StringUtil.isName(alias) == false)) {
throw new IllegalArgumentException("GET??: ?????? @column:value ? value??? , ??????" + " column:alias ? column ???1???????alias??alias????1???????????????");
}
}
 else {
if ((StringUtil.isName(ckeys[j]) == false || ckeys[j].startsWith("_"))) {
throw new IllegalArgumentException("?? " + ckeys[j] + " ????" + "?????? @column:\"column0,column1:alias;function0(arg0,arg1,...);function1(...):alias...\"" + " ??? arg ????1??? _ ??????????????");
}
}
}
origin=quote + origin + quote;
if (isKeyPrefix()) {
ckeys[j]=tableAlias + "." + origin;
if (isColumn && StringUtil.isEmpty(alias,true) == false) {
ckeys[j]+=" AS " + quote + alias + quote;
}
}
 else {
ckeys[j]=origin + (StringUtil.isEmpty(alias,true) ? "" : " AS " + quote + alias + quote);
}
}
}
if (isColumn) {
keys[i]=StringUtil.getString(ckeys);
}
 else {
String suffix=expression.substring(end + 1,expression.length());
String alias=suffix.startsWith(":") ? suffix.substring(1) : null;
if (StringUtil.isEmpty(alias,true)) {
if (suffix.isEmpty() == false) {
throw new IllegalArgumentException("GET??: ?????? @column:value ? value??? ; ??????" + " function(arg0,arg1,...):alias ? alias ???????1???????????????");
}
}
 else {
if (StringUtil.isEmpty(alias,true) == false && StringUtil.isName(alias) == false) {
throw new IllegalArgumentException("GET??: ?????? @column:value ? value??? ; ??????" + " function(arg0,arg1,...):alias ? alias ???1???????????????");
}
}
String origin=method + "(" + StringUtil.getString(ckeys) + ")";
keys[i]=origin + (StringUtil.isEmpty(alias,true) ? "" : " AS " + quote + alias + quote);
}
}
String c=StringUtil.getString(keys);
return (c.contains(":") == false ? c : c.replaceAll(":"," AS ")) + (StringUtil.isEmpty(joinColumn,true) ? "" : ", " + joinColumn);
default :
throw new UnsupportedOperationException("????????getColumnString ??? " + RequestMethod.getName(getMethod()) + " ? [GET,GETS,HEAD,HEADS,POST] ??ReuqestMethod?");
}
}
