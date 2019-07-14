/** 
 * Returns a  {@link BGFXViewStats.Buffer} view of the struct array pointed to by the {@code viewStats} field. 
 */
@NativeType("bgfx_view_stats_t *") public BGFXViewStats.Buffer viewStats(){
  return nviewStats(address());
}
