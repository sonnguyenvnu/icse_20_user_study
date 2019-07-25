/** 
 * Match a URI against the template. <p> If the URI matches against the pattern then the template variable to value map will be filled with template variables as keys and template values as values. <p>
 * @param uri the uri to match against the template.
 * @param templateVariableToValue the map where to put template variables (as keys)and template values (as values). The map is cleared before any entries are put.
 * @return true if the URI matches the template, otherwise false.
 * @throws IllegalArgumentException if the uri ortemplateVariableToValue is null.
 */
public final boolean match(CharSequence uri,Map<String,String> templateVariableToValue) throws IllegalArgumentException {
  if (templateVariableToValue == null)   throw new IllegalArgumentException();
  return pattern.match(uri,templateVariables,templateVariableToValue);
}
