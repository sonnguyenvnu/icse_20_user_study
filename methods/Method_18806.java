private static @Comparable.Type int getComparableType(TypeName typeName,com.facebook.litho.specmodels.model.TypeSpec typeSpec){
  if (typeName.equals(TypeName.FLOAT)) {
    return Comparable.FLOAT;
  }
 else   if (typeName.equals(TypeName.DOUBLE)) {
    return Comparable.DOUBLE;
  }
 else   if (typeName instanceof ArrayTypeName) {
    return Comparable.ARRAY;
  }
 else   if (typeName.isPrimitive()) {
    return Comparable.PRIMITIVE;
  }
 else   if (typeName.equals(ClassNames.COMPARABLE_DRAWABLE)) {
    return Comparable.COMPARABLE_DRAWABLE;
  }
 else   if (typeSpec.isSubInterface(ClassNames.COLLECTION)) {
    final int level=calculateLevelOfComponentInCollections((DeclaredTypeSpec)typeSpec);
switch (level) {
case 0:
      return Comparable.COLLECTION_COMPLEVEL_0;
case 1:
    return Comparable.COLLECTION_COMPLEVEL_1;
case 2:
  return Comparable.COLLECTION_COMPLEVEL_2;
case 3:
return Comparable.COLLECTION_COMPLEVEL_3;
case 4:
return Comparable.COLLECTION_COMPLEVEL_4;
default :
throw new IllegalStateException("Collection Component level not supported.");
}
}
 else if (typeName.equals(ClassNames.COMPONENT)) {
return Comparable.COMPONENT;
}
 else if (typeName.equals(ClassNames.SECTION)) {
return Comparable.SECTION;
}
 else if (typeName.equals(ClassNames.EVENT_HANDLER)) {
return Comparable.EVENT_HANDLER;
}
 else if (typeName instanceof ParameterizedTypeName && ((ParameterizedTypeName)typeName).rawType.equals(ClassNames.EVENT_HANDLER)) {
return Comparable.EVENT_HANDLER_IN_PARAMETERIZED_TYPE;
}
return Comparable.OTHER;
}
