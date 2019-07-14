@NativeType("uint64_t") public static long BGFX_STATE_BLEND_FUNC_RT_xE(@NativeType("uint64_t") long _src,@NativeType("uint64_t") long _dst,@NativeType("uint64_t") long _equation){
  return BGFX_STATE_BLEND_FUNC_RT_x(_src,_dst) | ((_equation >> BGFX_STATE_BLEND_EQUATION_SHIFT) << 8);
}
