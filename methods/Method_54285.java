@NativeType("uint64_t") public static long BGFX_STATE_BLEND_EQUATION_SEPARATE(@NativeType("uint64_t") long _rgb,@NativeType("uint64_t") long _a){
  return _rgb | (_a << 3);
}
