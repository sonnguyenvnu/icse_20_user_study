/** 
 * Returns  {@link Setter}. May return <code>null</code> if no matched setter is found.
 */
public Setter getSetter(final boolean declared){
  if (setters == null) {
    setters=new Setter[]{createSetter(false),createSetter(true)};
  }
  return setters[declared ? 1 : 0];
}
