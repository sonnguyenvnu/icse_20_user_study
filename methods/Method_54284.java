@NativeType("uint64_t") public static long BGFX_STATE_BLEND_FUNC_SEPARATE(@NativeType("uint64_t") long _srcRGB,@NativeType("uint64_t") long _dstRGB,@NativeType("uint64_t") long _srcA,@NativeType("uint64_t") long _dstA){
  return ((_srcRGB | (_dstRGB << 4))) | ((_srcA | (_dstA << 4)) << 8);
}
