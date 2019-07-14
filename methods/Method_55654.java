public static long memGetAddress(long ptr){
  return BITS64 ? UNSAFE.getLong(null,ptr) : UNSAFE.getInt(null,ptr) & 0xFFFF_FFFFL;
}
