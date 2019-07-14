/** 
 * Parses the header of a JVMS type_annotation structure to extract its target_type, target_info and target_path (the result is stored in the given context), and returns the start offset of the rest of the type_annotation structure.
 * @param context information about the class being parsed. This is where the extractedtarget_type and target_path must be stored.
 * @param typeAnnotationOffset the start offset of a type_annotation structure.
 * @return the start offset of the rest of the type_annotation structure.
 */
private int readTypeAnnotationTarget(final Context context,final int typeAnnotationOffset){
  int currentOffset=typeAnnotationOffset;
  int targetType=readInt(typeAnnotationOffset);
switch (targetType >>> 24) {
case TypeReference.CLASS_TYPE_PARAMETER:
case TypeReference.METHOD_TYPE_PARAMETER:
case TypeReference.METHOD_FORMAL_PARAMETER:
    targetType&=0xFFFF0000;
  currentOffset+=2;
break;
case TypeReference.FIELD:
case TypeReference.METHOD_RETURN:
case TypeReference.METHOD_RECEIVER:
targetType&=0xFF000000;
currentOffset+=1;
break;
case TypeReference.LOCAL_VARIABLE:
case TypeReference.RESOURCE_VARIABLE:
targetType&=0xFF000000;
int tableLength=readUnsignedShort(currentOffset + 1);
currentOffset+=3;
context.currentLocalVariableAnnotationRangeStarts=new Label[tableLength];
context.currentLocalVariableAnnotationRangeEnds=new Label[tableLength];
context.currentLocalVariableAnnotationRangeIndices=new int[tableLength];
for (int i=0; i < tableLength; ++i) {
int startPc=readUnsignedShort(currentOffset);
int length=readUnsignedShort(currentOffset + 2);
int index=readUnsignedShort(currentOffset + 4);
currentOffset+=6;
context.currentLocalVariableAnnotationRangeStarts[i]=createLabel(startPc,context.currentMethodLabels);
context.currentLocalVariableAnnotationRangeEnds[i]=createLabel(startPc + length,context.currentMethodLabels);
context.currentLocalVariableAnnotationRangeIndices[i]=index;
}
break;
case TypeReference.CAST:
case TypeReference.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
case TypeReference.METHOD_INVOCATION_TYPE_ARGUMENT:
case TypeReference.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
case TypeReference.METHOD_REFERENCE_TYPE_ARGUMENT:
targetType&=0xFF0000FF;
currentOffset+=4;
break;
case TypeReference.CLASS_EXTENDS:
case TypeReference.CLASS_TYPE_PARAMETER_BOUND:
case TypeReference.METHOD_TYPE_PARAMETER_BOUND:
case TypeReference.THROWS:
case TypeReference.EXCEPTION_PARAMETER:
targetType&=0xFFFFFF00;
currentOffset+=3;
break;
case TypeReference.INSTANCEOF:
case TypeReference.NEW:
case TypeReference.CONSTRUCTOR_REFERENCE:
case TypeReference.METHOD_REFERENCE:
targetType&=0xFF000000;
currentOffset+=3;
break;
default :
throw new IllegalArgumentException();
}
context.currentTypeAnnotationTarget=targetType;
int pathLength=readByte(currentOffset);
context.currentTypeAnnotationTargetPath=pathLength == 0 ? null : new TypePath(classFileBuffer,currentOffset);
return currentOffset + 1 + 2 * pathLength;
}
