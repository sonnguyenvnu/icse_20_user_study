protected String getClassName(){
switch (sort) {
case 0:
    return "void";
case 1:
  return "boolean";
case 2:
return "char";
case 3:
return "byte";
case 4:
return "short";
case 5:
return "int";
case 6:
return "float";
case 7:
return "long";
case 8:
return "double";
case 9:
Type elementType=getType(buf,off + getDimensions());
StringBuilder b=new StringBuilder(elementType.getClassName());
for (int i=getDimensions(); i > 0; --i) {
b.append("[]");
}
return b.toString();
default :
return new String(buf,off,len).replace('/','.');
}
}
