private void addType(Type t){
switch (t.getSort()) {
case Type.ARRAY:
    addType(t.getElementType());
  break;
case Type.OBJECT:
parseClassName(t.getClassName().replace('.','/'));
break;
default :
break;
}
}
