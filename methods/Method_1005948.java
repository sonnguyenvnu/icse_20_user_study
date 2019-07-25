/** 
 * Adds the specified  {@link PortBinding}(s) to the list of  {@link PortBinding}s.
 */
public void add(PortBinding... portBindings){
  for (  PortBinding binding : portBindings) {
    bind(binding.getExposedPort(),binding.getBinding());
  }
}
