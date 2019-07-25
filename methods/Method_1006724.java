/** 
 * Open the module, returning a  {@link ModuleReaderProxy}.
 * @return A {@link ModuleReaderProxy} for the module.
 * @throws IOException If the module cannot be opened.
 */
public ModuleReaderProxy open() throws IOException {
  return new ModuleReaderProxy(this);
}
