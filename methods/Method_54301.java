/** 
 * Sets the specified value to the  {@code profiler_end} field. 
 */
public BGFXCallbackVtbl profiler_end(@NativeType("void (*) (bgfx_callback_interface_t *)") BGFXProfilerEndI value){
  nprofiler_end(address(),value);
  return this;
}
