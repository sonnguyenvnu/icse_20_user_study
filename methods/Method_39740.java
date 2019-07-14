/** 
 * Visits non-array element value for annotation. Returns <code>true</code> if value is successfully processed.
 */
public static void visitElementValue(final MethodVisitor mv,final Object elementValue,final boolean boxPrimitives){
  if (elementValue instanceof String) {
    mv.visitLdcInsn(elementValue);
    return;
  }
  if (elementValue instanceof Type) {
    mv.visitLdcInsn(elementValue);
    return;
  }
  if (elementValue instanceof Class) {
    mv.visitLdcInsn(Type.getType((Class)elementValue));
    return;
  }
  if (elementValue instanceof Integer) {
    mv.visitLdcInsn(elementValue);
    if (boxPrimitives) {
      AsmUtil.valueOfInteger(mv);
    }
    return;
  }
  if (elementValue instanceof Long) {
    mv.visitLdcInsn(elementValue);
    if (boxPrimitives) {
      AsmUtil.valueOfLong(mv);
    }
    return;
  }
  if (elementValue instanceof Short) {
    mv.visitLdcInsn(elementValue);
    if (boxPrimitives) {
      AsmUtil.valueOfShort(mv);
    }
    return;
  }
  if (elementValue instanceof Byte) {
    mv.visitLdcInsn(elementValue);
    if (boxPrimitives) {
      AsmUtil.valueOfByte(mv);
    }
    return;
  }
  if (elementValue instanceof Float) {
    mv.visitLdcInsn(elementValue);
    if (boxPrimitives) {
      AsmUtil.valueOfFloat(mv);
    }
    return;
  }
  if (elementValue instanceof Double) {
    mv.visitLdcInsn(elementValue);
    if (boxPrimitives) {
      AsmUtil.valueOfDouble(mv);
    }
    return;
  }
  if (elementValue instanceof Character) {
    mv.visitLdcInsn(elementValue);
    if (boxPrimitives) {
      AsmUtil.valueOfCharacter(mv);
    }
    return;
  }
  if (elementValue instanceof Boolean) {
    mv.visitLdcInsn(elementValue);
    if (boxPrimitives) {
      AsmUtil.valueOfBoolean(mv);
    }
    return;
  }
  Class elementValueClass=elementValue.getClass();
  Class enumClass=ClassUtil.findEnum(elementValueClass);
  if (enumClass != null) {
    try {
      String typeRef=AsmUtil.typeToTyperef(enumClass);
      String typeSignature=AsmUtil.typeToSignature(enumClass);
      Method nameMethod=elementValue.getClass().getMethod("name");
      String name=(String)nameMethod.invoke(elementValue);
      mv.visitFieldInsn(GETSTATIC,typeSignature,name,typeRef);
      return;
    }
 catch (    Exception ignore) {
    }
  }
  throw new ProxettaException("Unsupported annotation type: " + elementValue.getClass());
}
