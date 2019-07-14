/** 
 * Sets the mount content that this  {@link AnimatedPropertyNode} updates a value on. 
 */
public void setMountContentGroup(OutputUnitsAffinityGroup<Object> mountContentGroup){
  setMountContentGroupInner(mountContentGroup);
  setValueInner(getValue());
}
