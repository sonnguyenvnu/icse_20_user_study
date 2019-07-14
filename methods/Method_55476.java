public static int lengthSafe(@Nullable long[] array){
  return array == null ? 0 : array.length;
}
