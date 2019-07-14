@NativeType("bool") public static boolean BGFX_HANDLE_IS_VALID(@NativeType("uint16_t") short h){
  return Short.toUnsignedInt(h) != 0xFFFF;
}
