/** 
 * Specifies if an exception should be thrown if two beans with same exception are registered with this container.
 */
public PetiteConfig setDetectDuplicatedBeanNames(final boolean detectDuplicatedBeanNames){
  this.detectDuplicatedBeanNames=detectDuplicatedBeanNames;
  return this;
}
