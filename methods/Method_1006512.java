/** 
 * ????????
 * @param data
 * @return {AjaxResult}
 */
public AjaxResult success(Object data){
  this.data=data;
  this.code=0;
  return this;
}
