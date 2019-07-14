public static int lengthSafe(@Nullable float[] array){
  return array == null ? 0 : array.length;
}
