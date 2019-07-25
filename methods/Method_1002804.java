/** 
 * Binds the specified  {@link CompositeServiceEntry}.
 */
protected T service(CompositeServiceEntry<I,O> entry){
  services.add(requireNonNull(entry,"entry"));
  return self();
}
