/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  check(memGetAddress(struct + BGFXCallbackVtbl.FATAL));
  check(memGetAddress(struct + BGFXCallbackVtbl.TRACE_VARGS));
  check(memGetAddress(struct + BGFXCallbackVtbl.PROFILER_BEGIN));
  check(memGetAddress(struct + BGFXCallbackVtbl.PROFILER_BEGIN_LITERAL));
  check(memGetAddress(struct + BGFXCallbackVtbl.PROFILER_END));
  check(memGetAddress(struct + BGFXCallbackVtbl.CACHE_READ_SIZE));
  check(memGetAddress(struct + BGFXCallbackVtbl.CACHE_READ));
  check(memGetAddress(struct + BGFXCallbackVtbl.CACHE_WRITE));
  check(memGetAddress(struct + BGFXCallbackVtbl.SCREEN_SHOT));
  check(memGetAddress(struct + BGFXCallbackVtbl.CAPTURE_BEGIN));
  check(memGetAddress(struct + BGFXCallbackVtbl.CAPTURE_END));
  check(memGetAddress(struct + BGFXCallbackVtbl.CAPTURE_FRAME));
}
