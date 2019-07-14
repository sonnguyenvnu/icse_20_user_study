/** 
 * Dispatches compute indirect.
 * @param _id             view id
 * @param _program        compute program
 * @param _indirectHandle indirect buffer
 * @param _start          first element in indirect buffer
 * @param _num            number of dispatches
 */
public static void bgfx_dispatch_indirect(@NativeType("bgfx_view_id_t") int _id,@NativeType("bgfx_program_handle_t") short _program,@NativeType("bgfx_indirect_buffer_handle_t") short _indirectHandle,@NativeType("uint16_t") int _start,@NativeType("uint16_t") int _num){
  nbgfx_dispatch_indirect((short)_id,_program,_indirectHandle,(short)_start,(short)_num);
}
