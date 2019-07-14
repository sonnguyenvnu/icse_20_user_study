/** 
 * Returns the value of the  {@code profiler_end} field. 
 */
@NativeType("void (*) (bgfx_callback_interface_t *)") public BGFXProfilerEnd profiler_end(){
  return nprofiler_end(address());
}
