/** 
 * A registration action, typically used with  {@link RegistrySpec#with(Action)}. <p> Registers this object with the type  {@code RenderableDecorator<T>} (where {@code T} is the value of {@link #getType()}), not its concrete type.
 * @return a registration action
 */
default Action<RegistrySpec> register(){
  return (registrySpec) -> registrySpec.add(typeOf(getType()),this);
}
