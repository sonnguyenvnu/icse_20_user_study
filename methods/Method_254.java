/** 
 * Returns a method name from the  {@code android.content.res.Resources} class for array resourcebinding, null if the element type is not supported.
 */
private static @Nullable FieldResourceBinding.Type getArrayResourceMethodName(Element element){
  TypeMirror typeMirror=element.asType();
  if (TYPED_ARRAY_TYPE.equals(typeMirror.toString())) {
    return FieldResourceBinding.Type.TYPED_ARRAY;
  }
  if (TypeKind.ARRAY.equals(typeMirror.getKind())) {
    ArrayType arrayType=(ArrayType)typeMirror;
    String componentType=arrayType.getComponentType().toString();
    if (STRING_TYPE.equals(componentType)) {
      return FieldResourceBinding.Type.STRING_ARRAY;
    }
 else     if ("int".equals(componentType)) {
      return FieldResourceBinding.Type.INT_ARRAY;
    }
 else     if ("java.lang.CharSequence".equals(componentType)) {
      return FieldResourceBinding.Type.TEXT_ARRAY;
    }
  }
  return null;
}
