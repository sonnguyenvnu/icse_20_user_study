/** 
 * Returns a  {@link BGFXEncoderStats.Buffer} view of the struct array pointed to by the {@code encoderStats} field. 
 */
@NativeType("bgfx_encoder_stats_t *") public BGFXEncoderStats.Buffer encoderStats(){
  return nencoderStats(address());
}
