/** 
 * <p> Append a <code>hashCode</code> for a <code>boolean</code>. </p> <p> This adds <code>1</code> when true, and <code>0</code> when false to the <code>hashCode</code>. </p> <p> This is in contrast to the standard <code>java.lang.Boolean.hashCode</code> handling, which computes a <code>hashCode</code> value of <code>1231</code> for <code>java.lang.Boolean</code> instances that represent <code>true</code> or <code>1237</code> for <code>java.lang.Boolean</code> instances that represent <code>false</code>. </p> <p> This is in accordance with the <quote>Effective Java</quote> design. </p>
 * @param value the boolean to add to the <code>hashCode</code>
 * @return this
 */
public HashCodeBuilder append(boolean value){
  iTotal=iTotal * iConstant + (value ? 0 : 1);
  return this;
}
