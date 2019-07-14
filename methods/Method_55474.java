public static int lengthSafe(@Nullable short[] array){
  return array == null ? 0 : array.length;
}
