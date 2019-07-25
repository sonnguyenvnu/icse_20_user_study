/** 
 * Emits one or more key/value pairs for the given metadata value. By default this method simply emits the given value with this key's label, but it can be overridden to emit multiple key/value pairs if necessary. Note that if multiple key/value pairs are emitted, the following best-practice should be followed: <ul> <li>Key names should be of the form  {@code "<label>.<suffix>"}. <li>Suffixes may only contain lower case ASCII letters and underscore (i.e. [a-z_]). </ul>
 */
public void emit(Object value,KeyValueHandler out){
  out.handle(getLabel(),value);
}
