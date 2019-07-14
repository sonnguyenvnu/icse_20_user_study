/** 
 * Dispatches compute.
 * @param _encoder the encoder
 * @param _id      view id
 * @param _handle  compute program
 * @param _numX    number of groups X
 * @param _numY    number of groups Y
 * @param _numZ    number of groups Z
 */
public static void bgfx_encoder_dispatch(@NativeType("struct bgfx_encoder_s *") long _encoder,@NativeType("bgfx_view_id_t") int _id,@NativeType("bgfx_program_handle_t") short _handle,@NativeType("uint32_t") int _numX,@NativeType("uint32_t") int _numY,@NativeType("uint32_t") int _numZ){
  nbgfx_encoder_dispatch(_encoder,(short)_id,_handle,_numX,_numY,_numZ);
}
