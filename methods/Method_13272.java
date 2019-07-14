protected String createDescriptor(JavaParser.TypeContext typeContext,int dimension){
  if (typeContext == null) {
    return "V";
  }
 else {
    dimension+=countDimension(typeContext.children);
    JavaParser.PrimitiveTypeContext primitive=typeContext.primitiveType();
    String name;
    if (primitive == null) {
      JavaParser.ClassOrInterfaceTypeContext type=typeContext.classOrInterfaceType();
      List<JavaParser.TypeArgumentsContext> typeArgumentsContexts=type.typeArguments();
      if (typeArgumentsContexts.size() == 1) {
        JavaParser.TypeArgumentsContext typeArgumentsContext=typeArgumentsContexts.get(0);
        List<JavaParser.TypeArgumentContext> typeArguments=typeArgumentsContext.typeArgument();
      }
 else       if (typeArgumentsContexts.size() > 1) {
        throw new RuntimeException("UNEXPECTED");
      }
      name="L" + resolveInternalTypeName(type.Identifier()) + ";";
    }
 else {
switch (primitive.getText()) {
case "boolean":
        name="Z";
      break;
case "byte":
    name="B";
  break;
case "char":
name="C";
break;
case "double":
name="D";
break;
case "float":
name="F";
break;
case "int":
name="I";
break;
case "long":
name="J";
break;
case "short":
name="S";
break;
case "void":
name="V";
break;
default :
throw new RuntimeException("UNEXPECTED PRIMITIVE");
}
}
switch (dimension) {
case 0:
return name;
case 1:
return "[" + name;
case 2:
return "[[" + name;
default :
return new String(new char[dimension]).replace('\0','[') + name;
}
}
}
