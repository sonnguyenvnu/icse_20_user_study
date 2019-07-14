public static int lengthSafe(@Nullable double[] array){
  return array == null ? 0 : array.length;
}
