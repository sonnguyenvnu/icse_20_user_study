/** 
 * Returns a  {@link BGFXCapsGPU} view of the struct at the specified index of the {@code gpu} field. 
 */
@NativeType("bgfx_caps_gpu_t") public BGFXCapsGPU gpu(int index){
  return ngpu(address(),index);
}
