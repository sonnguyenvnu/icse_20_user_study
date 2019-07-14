/** 
 * Returns number of requested or maximum available instance buffer slots.
 * @param _num    number of required instances
 * @param _stride stride per instance
 */
@NativeType("uint32_t") public static int bgfx_get_avail_instance_data_buffer(@NativeType("uint32_t") int _num,@NativeType("uint16_t") int _stride){
  return nbgfx_get_avail_instance_data_buffer(_num,(short)_stride);
}
