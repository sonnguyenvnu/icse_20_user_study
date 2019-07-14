/** 
 * Returns  {@link PropsEntries builder} for entries {@link #iterator() itertor}.
 */
public PropsEntries entries(){
  initialize();
  return new PropsEntries(this);
}
