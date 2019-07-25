/** 
 * This method should be called right after creation of this class in order to complete initialization process.
 * @return this
 */
public ReadableModelProperty init(){
  synchronizeViewWithModel();
  return this;
}
