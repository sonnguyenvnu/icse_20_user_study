public static int lengthSafe(@Nullable int[] array){
  return array == null ? 0 : array.length;
}
