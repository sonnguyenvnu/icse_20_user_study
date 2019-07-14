private static TypeName bestGuess(String type){
switch (type) {
case "void":
    return TypeName.VOID;
case "boolean":
  return TypeName.BOOLEAN;
case "byte":
return TypeName.BYTE;
case "char":
return TypeName.CHAR;
case "double":
return TypeName.DOUBLE;
case "float":
return TypeName.FLOAT;
case "int":
return TypeName.INT;
case "long":
return TypeName.LONG;
case "short":
return TypeName.SHORT;
default :
int left=type.indexOf('<');
if (left != -1) {
ClassName typeClassName=ClassName.bestGuess(type.substring(0,left));
List<TypeName> typeArguments=new ArrayList<>();
do {
typeArguments.add(WildcardTypeName.subtypeOf(Object.class));
left=type.indexOf('<',left + 1);
}
 while (left != -1);
return ParameterizedTypeName.get(typeClassName,typeArguments.toArray(new TypeName[typeArguments.size()]));
}
return ClassName.bestGuess(type);
}
}
