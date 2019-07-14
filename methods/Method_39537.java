/** 
 * Reads a JVMS 'element_value' structure and makes the given visitor visit it.
 * @param annotationVisitor the visitor that must visit the element_value structure.
 * @param elementValueOffset the start offset in {@link #classFileBuffer} of the element_valuestructure to be read.
 * @param elementName the name of the element_value structure to be read, or {@literal null}.
 * @param charBuffer the buffer used to read strings in the constant pool.
 * @return the end offset of the JVMS 'element_value' structure.
 */
private int readElementValue(final AnnotationVisitor annotationVisitor,final int elementValueOffset,final String elementName,final char[] charBuffer){
  int currentOffset=elementValueOffset;
  if (annotationVisitor == null) {
switch (classFileBuffer[currentOffset] & 0xFF) {
case 'e':
      return currentOffset + 5;
case '@':
    return readElementValues(null,currentOffset + 3,true,charBuffer);
case '[':
  return readElementValues(null,currentOffset + 1,false,charBuffer);
default :
return currentOffset + 3;
}
}
switch (classFileBuffer[currentOffset++] & 0xFF) {
case 'B':
annotationVisitor.visit(elementName,(byte)readInt(cpInfoOffsets[readUnsignedShort(currentOffset)]));
currentOffset+=2;
break;
case 'C':
annotationVisitor.visit(elementName,(char)readInt(cpInfoOffsets[readUnsignedShort(currentOffset)]));
currentOffset+=2;
break;
case 'D':
case 'F':
case 'I':
case 'J':
annotationVisitor.visit(elementName,readConst(readUnsignedShort(currentOffset),charBuffer));
currentOffset+=2;
break;
case 'S':
annotationVisitor.visit(elementName,(short)readInt(cpInfoOffsets[readUnsignedShort(currentOffset)]));
currentOffset+=2;
break;
case 'Z':
annotationVisitor.visit(elementName,readInt(cpInfoOffsets[readUnsignedShort(currentOffset)]) == 0 ? Boolean.FALSE : Boolean.TRUE);
currentOffset+=2;
break;
case 's':
annotationVisitor.visit(elementName,readUTF8(currentOffset,charBuffer));
currentOffset+=2;
break;
case 'e':
annotationVisitor.visitEnum(elementName,readUTF8(currentOffset,charBuffer),readUTF8(currentOffset + 2,charBuffer));
currentOffset+=4;
break;
case 'c':
annotationVisitor.visit(elementName,Type.getType(readUTF8(currentOffset,charBuffer)));
currentOffset+=2;
break;
case '@':
currentOffset=readElementValues(annotationVisitor.visitAnnotation(elementName,readUTF8(currentOffset,charBuffer)),currentOffset + 2,true,charBuffer);
break;
case '[':
int numValues=readUnsignedShort(currentOffset);
currentOffset+=2;
if (numValues == 0) {
return readElementValues(annotationVisitor.visitArray(elementName),currentOffset - 2,false,charBuffer);
}
switch (classFileBuffer[currentOffset] & 0xFF) {
case 'B':
byte[] byteValues=new byte[numValues];
for (int i=0; i < numValues; i++) {
byteValues[i]=(byte)readInt(cpInfoOffsets[readUnsignedShort(currentOffset + 1)]);
currentOffset+=3;
}
annotationVisitor.visit(elementName,byteValues);
break;
case 'Z':
boolean[] booleanValues=new boolean[numValues];
for (int i=0; i < numValues; i++) {
booleanValues[i]=readInt(cpInfoOffsets[readUnsignedShort(currentOffset + 1)]) != 0;
currentOffset+=3;
}
annotationVisitor.visit(elementName,booleanValues);
break;
case 'S':
short[] shortValues=new short[numValues];
for (int i=0; i < numValues; i++) {
shortValues[i]=(short)readInt(cpInfoOffsets[readUnsignedShort(currentOffset + 1)]);
currentOffset+=3;
}
annotationVisitor.visit(elementName,shortValues);
break;
case 'C':
char[] charValues=new char[numValues];
for (int i=0; i < numValues; i++) {
charValues[i]=(char)readInt(cpInfoOffsets[readUnsignedShort(currentOffset + 1)]);
currentOffset+=3;
}
annotationVisitor.visit(elementName,charValues);
break;
case 'I':
int[] intValues=new int[numValues];
for (int i=0; i < numValues; i++) {
intValues[i]=readInt(cpInfoOffsets[readUnsignedShort(currentOffset + 1)]);
currentOffset+=3;
}
annotationVisitor.visit(elementName,intValues);
break;
case 'J':
long[] longValues=new long[numValues];
for (int i=0; i < numValues; i++) {
longValues[i]=readLong(cpInfoOffsets[readUnsignedShort(currentOffset + 1)]);
currentOffset+=3;
}
annotationVisitor.visit(elementName,longValues);
break;
case 'F':
float[] floatValues=new float[numValues];
for (int i=0; i < numValues; i++) {
floatValues[i]=Float.intBitsToFloat(readInt(cpInfoOffsets[readUnsignedShort(currentOffset + 1)]));
currentOffset+=3;
}
annotationVisitor.visit(elementName,floatValues);
break;
case 'D':
double[] doubleValues=new double[numValues];
for (int i=0; i < numValues; i++) {
doubleValues[i]=Double.longBitsToDouble(readLong(cpInfoOffsets[readUnsignedShort(currentOffset + 1)]));
currentOffset+=3;
}
annotationVisitor.visit(elementName,doubleValues);
break;
default :
currentOffset=readElementValues(annotationVisitor.visitArray(elementName),currentOffset - 2,false,charBuffer);
break;
}
break;
default :
throw new IllegalArgumentException();
}
return currentOffset;
}
