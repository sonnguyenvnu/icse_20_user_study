/** 
 * ?????????????????????
 * @return the validation
 */
public boolean hasValidation(){
  if (validation) {
    return true;
  }
  if (CommonUtils.isNotEmpty(methods)) {
    for (    MethodConfig methodConfig : methods.values()) {
      if (CommonUtils.isTrue(methodConfig.getValidation())) {
        return true;
      }
    }
  }
  return false;
}
