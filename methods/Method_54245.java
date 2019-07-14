/** 
 * Reserves  {@code _num} matrices in internal matrix cache.<p>Pointer returned can be modifed until  {@link #bgfx_frame frame} is called.</p>
 * @param _encoder   the encoder
 * @param _transform pointer to {@link BGFXTransform} structure
 * @param _num       number of matrices
 * @return index into matrix cache
 */
@NativeType("uint32_t") public static int bgfx_encoder_alloc_transform(@NativeType("struct bgfx_encoder_s *") long _encoder,@NativeType("bgfx_transform_t *") BGFXTransform _transform,@NativeType("uint16_t") int _num){
  return nbgfx_encoder_alloc_transform(_encoder,_transform.address(),(short)_num);
}
