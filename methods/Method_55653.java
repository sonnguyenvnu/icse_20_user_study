public static boolean memGetBoolean(long ptr){
  return UNSAFE.getByte(null,ptr) != 0;
}
