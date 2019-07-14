/** 
 * Computes the implicit frame of the method currently being parsed (as defined in the given {@link Context}) and stores it in the given context.
 * @param context information about the class being parsed.
 */
private void computeImplicitFrame(final Context context){
  String methodDescriptor=context.currentMethodDescriptor;
  Object[] locals=context.currentFrameLocalTypes;
  int numLocal=0;
  if ((context.currentMethodAccessFlags & Opcodes.ACC_STATIC) == 0) {
    if ("<init>".equals(context.currentMethodName)) {
      locals[numLocal++]=Opcodes.UNINITIALIZED_THIS;
    }
 else {
      locals[numLocal++]=readClass(header + 2,context.charBuffer);
    }
  }
  int currentMethodDescritorOffset=1;
  while (true) {
    int currentArgumentDescriptorStartOffset=currentMethodDescritorOffset;
switch (methodDescriptor.charAt(currentMethodDescritorOffset++)) {
case 'Z':
case 'C':
case 'B':
case 'S':
case 'I':
      locals[numLocal++]=Opcodes.INTEGER;
    break;
case 'F':
  locals[numLocal++]=Opcodes.FLOAT;
break;
case 'J':
locals[numLocal++]=Opcodes.LONG;
break;
case 'D':
locals[numLocal++]=Opcodes.DOUBLE;
break;
case '[':
while (methodDescriptor.charAt(currentMethodDescritorOffset) == '[') {
++currentMethodDescritorOffset;
}
if (methodDescriptor.charAt(currentMethodDescritorOffset) == 'L') {
++currentMethodDescritorOffset;
while (methodDescriptor.charAt(currentMethodDescritorOffset) != ';') {
++currentMethodDescritorOffset;
}
}
locals[numLocal++]=methodDescriptor.substring(currentArgumentDescriptorStartOffset,++currentMethodDescritorOffset);
break;
case 'L':
while (methodDescriptor.charAt(currentMethodDescritorOffset) != ';') {
++currentMethodDescritorOffset;
}
locals[numLocal++]=methodDescriptor.substring(currentArgumentDescriptorStartOffset + 1,currentMethodDescritorOffset++);
break;
default :
context.currentFrameLocalCount=numLocal;
return;
}
}
}
