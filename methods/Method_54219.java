/** 
 * Creates program with compute shader.
 * @param _csh            compute shader
 * @param _destroyShaders if true, shader will be destroyed when program is destroyed
 */
@NativeType("bgfx_program_handle_t") public static short bgfx_create_compute_program(@NativeType("bgfx_shader_handle_t") short _csh,@NativeType("bool") boolean _destroyShaders){
  long __functionAddress=Functions.create_compute_program;
  return invokeS(_csh,_destroyShaders,__functionAddress);
}
