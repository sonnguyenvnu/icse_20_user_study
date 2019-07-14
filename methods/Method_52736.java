/** 
 * @return boolean - true if the element has a namespace-prefix, falseotherwise
 */
public boolean isHasNamespacePrefix(){
  return name.indexOf(':') >= 0;
}
