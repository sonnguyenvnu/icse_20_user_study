/** 
 * Converts primitive array to target array.
 */
protected byte[] convertPrimitiveArrayToArray(final Object value,final Class primitiveComponentType){
  byte[] result=null;
  if (primitiveComponentType == byte.class) {
    return (byte[])value;
  }
  if (primitiveComponentType == int.class) {
    final int[] array=(int[])value;
    result=new byte[array.length];
    for (int i=0; i < array.length; i++) {
      result[i]=(byte)array[i];
    }
  }
 else   if (primitiveComponentType == long.class) {
    final long[] array=(long[])value;
    result=new byte[array.length];
    for (int i=0; i < array.length; i++) {
      result[i]=(byte)array[i];
    }
  }
 else   if (primitiveComponentType == float.class) {
    final float[] array=(float[])value;
    result=new byte[array.length];
    for (int i=0; i < array.length; i++) {
      result[i]=(byte)array[i];
    }
  }
 else   if (primitiveComponentType == double.class) {
    final double[] array=(double[])value;
    result=new byte[array.length];
    for (int i=0; i < array.length; i++) {
      result[i]=(byte)array[i];
    }
  }
 else   if (primitiveComponentType == short.class) {
    final short[] array=(short[])value;
    result=new byte[array.length];
    for (int i=0; i < array.length; i++) {
      result[i]=(byte)array[i];
    }
  }
 else   if (primitiveComponentType == char.class) {
    final char[] array=(char[])value;
    result=new byte[array.length];
    for (int i=0; i < array.length; i++) {
      result[i]=(byte)array[i];
    }
  }
 else   if (primitiveComponentType == boolean.class) {
    final boolean[] array=(boolean[])value;
    result=new byte[array.length];
    for (int i=0; i < array.length; i++) {
      result[i]=(byte)(array[i] ? 1 : 0);
    }
  }
  return result;
}
