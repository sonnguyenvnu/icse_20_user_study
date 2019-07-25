/** 
 * <p>Adds a named long property.</p>
 * @param name The property's name.
 * @param value The property's value.
 * @return the property that was stored under the specified name before, or<code>null</code> if there was no such property before.
 */
public Object put(final String name,final Long value){
  final MutableProperty p=new MutableProperty();
  p.setID(-1);
  p.setType(Variant.VT_I8);
  p.setValue(value);
  final CustomProperty cp=new CustomProperty(p,name);
  return put(cp);
}
