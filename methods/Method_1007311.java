public static void aastore(TypeData array,TypeData value,ClassPool cp) throws BadBytecode {
  if (array instanceof AbsTypeVar)   if (!value.isNullType())   ((AbsTypeVar)array).merge(ArrayType.make(value));
  if (value instanceof AbsTypeVar)   if (array instanceof AbsTypeVar)   ArrayElement.make(array);
 else   if (array instanceof ClassName) {
    if (!array.isNullType()) {
      String type=ArrayElement.typeName(array.getName());
      value.setType(type,cp);
    }
  }
 else   throw new BadBytecode("bad AASTORE: " + array);
}
