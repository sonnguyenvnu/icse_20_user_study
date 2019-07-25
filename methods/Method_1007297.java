private static String ldc(ConstPool pool,int index){
  int tag=pool.getTag(index);
switch (tag) {
case ConstPool.CONST_String:
    return "#" + index + " = \"" + pool.getStringInfo(index) + "\"";
case ConstPool.CONST_Integer:
  return "#" + index + " = int " + pool.getIntegerInfo(index);
case ConstPool.CONST_Float:
return "#" + index + " = float " + pool.getFloatInfo(index);
case ConstPool.CONST_Long:
return "#" + index + " = long " + pool.getLongInfo(index);
case ConstPool.CONST_Double:
return "#" + index + " = int " + pool.getDoubleInfo(index);
case ConstPool.CONST_Class:
return classInfo(pool,index);
default :
throw new RuntimeException("bad LDC: " + tag);
}
}
