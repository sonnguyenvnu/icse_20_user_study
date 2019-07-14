/** 
 * ?????
 * @param msgFormat
 * @param args
 * @return
 */
public BizException newInstance(String msgFormat,Object... args){
  return new BizException(this.code,msgFormat,args);
}
