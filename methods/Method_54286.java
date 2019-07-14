@NativeType("uint64_t") public static long BGFX_STATE_BLEND_FUNC_RT_x(@NativeType("uint64_t") long _src,@NativeType("uint64_t") long _dst){
  return (_src >> BGFX_STATE_BLEND_SHIFT) | ((_dst >> BGFX_STATE_BLEND_SHIFT) << 4);
}
