/** 
 * Given a class configures the binding between a class and a Renderer class.
 * @param clazz to bind.
 * @param prototype used as Renderer.
 * @return the current RendererBuilder instance.
 */
public <G extends T>RendererBuilder<T> bind(Class<G> clazz,Renderer<? extends G> prototype){
  if (clazz == null || prototype == null) {
    throw new IllegalArgumentException("The binding RecyclerView binding can't be configured using null instances");
  }
  prototypes.add(prototype);
  binding.put(clazz,prototype.getClass());
  return this;
}
