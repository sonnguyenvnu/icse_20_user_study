public int index(Table t,Integer defaultIndex){
switch (position) {
case FIRST:
    return 0;
case DEFAULT:
  if (defaultIndex != null)   return defaultIndex;
 else   return t.getColumnList().size();
case AFTER:
int afterIdx=t.findColumnIndex(afterColumn);
if (afterIdx == -1) return AFTER_NOT_FOUND;
return afterIdx + 1;
}
return -1;
}
