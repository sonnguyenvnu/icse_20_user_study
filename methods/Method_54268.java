/** 
 * Sets index buffer for draw primitive.
 * @param _handle     dynamic index buffer
 * @param _firstIndex first index to render
 * @param _numIndices number of indices to render
 */
public static void bgfx_set_dynamic_index_buffer(@NativeType("bgfx_dynamic_index_buffer_handle_t") short _handle,@NativeType("uint32_t") int _firstIndex,@NativeType("uint32_t") int _numIndices){
  long __functionAddress=Functions.set_dynamic_index_buffer;
  invokeV(_handle,_firstIndex,_numIndices,__functionAddress);
}
