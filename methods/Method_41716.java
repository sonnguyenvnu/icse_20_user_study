/** 
 * <p> Return the string representation of the key. The format will be: &lt;group&gt;.&lt;name&gt;. </p>
 * @return the string representation of the key
 */
@Override public String toString(){
  return getGroup() + '.' + getName();
}
