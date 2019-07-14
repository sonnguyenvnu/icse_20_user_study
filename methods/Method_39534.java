/** 
 * Parses a Runtime[In]VisibleTypeAnnotations attribute to find the offset of each type_annotation entry it contains, to find the corresponding labels, and to visit the try catch block annotations.
 * @param methodVisitor the method visitor to be used to visit the try catch block annotations.
 * @param context information about the class being parsed.
 * @param runtimeTypeAnnotationsOffset the start offset of a Runtime[In]VisibleTypeAnnotationsattribute, excluding the attribute_info's attribute_name_index and attribute_length fields.
 * @param visible true if the attribute to parse is a RuntimeVisibleTypeAnnotations attribute,false it is a RuntimeInvisibleTypeAnnotations attribute.
 * @return the start offset of each entry of the Runtime[In]VisibleTypeAnnotations_attribute's'annotations' array field.
 */
private int[] readTypeAnnotations(final MethodVisitor methodVisitor,final Context context,final int runtimeTypeAnnotationsOffset,final boolean visible){
  char[] charBuffer=context.charBuffer;
  int currentOffset=runtimeTypeAnnotationsOffset;
  int[] typeAnnotationsOffsets=new int[readUnsignedShort(currentOffset)];
  currentOffset+=2;
  for (int i=0; i < typeAnnotationsOffsets.length; ++i) {
    typeAnnotationsOffsets[i]=currentOffset;
    int targetType=readInt(currentOffset);
switch (targetType >>> 24) {
case TypeReference.LOCAL_VARIABLE:
case TypeReference.RESOURCE_VARIABLE:
      int tableLength=readUnsignedShort(currentOffset + 1);
    currentOffset+=3;
  while (tableLength-- > 0) {
    int startPc=readUnsignedShort(currentOffset);
    int length=readUnsignedShort(currentOffset + 2);
    currentOffset+=6;
    createLabel(startPc,context.currentMethodLabels);
    createLabel(startPc + length,context.currentMethodLabels);
  }
break;
case TypeReference.CAST:
case TypeReference.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
case TypeReference.METHOD_INVOCATION_TYPE_ARGUMENT:
case TypeReference.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
case TypeReference.METHOD_REFERENCE_TYPE_ARGUMENT:
currentOffset+=4;
break;
case TypeReference.CLASS_EXTENDS:
case TypeReference.CLASS_TYPE_PARAMETER_BOUND:
case TypeReference.METHOD_TYPE_PARAMETER_BOUND:
case TypeReference.THROWS:
case TypeReference.EXCEPTION_PARAMETER:
case TypeReference.INSTANCEOF:
case TypeReference.NEW:
case TypeReference.CONSTRUCTOR_REFERENCE:
case TypeReference.METHOD_REFERENCE:
currentOffset+=3;
break;
case TypeReference.CLASS_TYPE_PARAMETER:
case TypeReference.METHOD_TYPE_PARAMETER:
case TypeReference.METHOD_FORMAL_PARAMETER:
case TypeReference.FIELD:
case TypeReference.METHOD_RETURN:
case TypeReference.METHOD_RECEIVER:
default :
throw new IllegalArgumentException();
}
int pathLength=readByte(currentOffset);
if ((targetType >>> 24) == TypeReference.EXCEPTION_PARAMETER) {
TypePath path=pathLength == 0 ? null : new TypePath(classFileBuffer,currentOffset);
currentOffset+=1 + 2 * pathLength;
String annotationDescriptor=readUTF8(currentOffset,charBuffer);
currentOffset+=2;
currentOffset=readElementValues(methodVisitor.visitTryCatchAnnotation(targetType & 0xFFFFFF00,path,annotationDescriptor,visible),currentOffset,true,charBuffer);
}
 else {
currentOffset+=3 + 2 * pathLength;
currentOffset=readElementValues(null,currentOffset,true,charBuffer);
}
}
return typeAnnotationsOffsets;
}
