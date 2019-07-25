/** 
 * Adds a new  {@link PortBinding} for the specified {@link ExposedPort} and {@link Binding} to the current bindings.
 */
public void bind(ExposedPort exposedPort,Binding binding){
  if (ports.containsKey(exposedPort)) {
    Binding[] bindings=ports.get(exposedPort);
    ports.put(exposedPort,(Binding[])ArrayUtils.add(bindings,binding));
  }
 else {
    if (binding == null) {
      ports.put(exposedPort,null);
    }
 else {
      ports.put(exposedPort,new Binding[]{binding});
    }
  }
}
