@Override public Type inline(Inliner inliner){
  Symtab symtab=inliner.symtab();
switch (getKind()) {
case BYTE:
    return symtab.byteType;
case SHORT:
  return symtab.shortType;
case INT:
return symtab.intType;
case LONG:
return symtab.longType;
case FLOAT:
return symtab.floatType;
case DOUBLE:
return symtab.doubleType;
case BOOLEAN:
return symtab.booleanType;
case CHAR:
return symtab.charType;
case VOID:
return symtab.voidType;
case NULL:
return symtab.botType;
default :
throw new AssertionError();
}
}
