/** 
 * Returns name of renderer.
 * @param _type the renderer type. One of:<br><table><tr><td>{@link #BGFX_RENDERER_TYPE_NOOP RENDERER_TYPE_NOOP}</td><td> {@link #BGFX_RENDERER_TYPE_DIRECT3D9 RENDERER_TYPE_DIRECT3D9}</td><td> {@link #BGFX_RENDERER_TYPE_DIRECT3D11 RENDERER_TYPE_DIRECT3D11}</td><td> {@link #BGFX_RENDERER_TYPE_DIRECT3D12 RENDERER_TYPE_DIRECT3D12}</td></tr><tr><td> {@link #BGFX_RENDERER_TYPE_GNM RENDERER_TYPE_GNM}</td><td> {@link #BGFX_RENDERER_TYPE_METAL RENDERER_TYPE_METAL}</td><td> {@link #BGFX_RENDERER_TYPE_NVN RENDERER_TYPE_NVN}</td><td> {@link #BGFX_RENDERER_TYPE_OPENGLES RENDERER_TYPE_OPENGLES}</td></tr><tr><td> {@link #BGFX_RENDERER_TYPE_OPENGL RENDERER_TYPE_OPENGL}</td><td> {@link #BGFX_RENDERER_TYPE_VULKAN RENDERER_TYPE_VULKAN}</td><td> {@link #BGFX_RENDERER_TYPE_COUNT RENDERER_TYPE_COUNT}</td></tr></table>
 */
@Nullable @NativeType("char const *") public static String bgfx_get_renderer_name(@NativeType("bgfx_renderer_type_t") int _type){
  long __result=nbgfx_get_renderer_name(_type);
  return memASCIISafe(__result);
}
