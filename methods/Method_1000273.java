/** 
 * ??????, ???????, ??: ??,??,???,??? -- 4????
 */
public void filter(InterceptorChain chain) throws Throwable {
  try {
    if (beforeInvoke(chain.getCallingObj(),chain.getCallingMethod(),chain.getArgs()))     chain.doChain();
    Object obj=afterInvoke(chain.getCallingObj(),chain.getReturn(),chain.getCallingMethod(),chain.getArgs());
    chain.setReturnValue(obj);
  }
 catch (  Exception e) {
    if (whenException(e,chain.getCallingObj(),chain.getCallingMethod(),chain.getArgs()))     throw e;
  }
catch (  Throwable e) {
    if (whenError(e,chain.getCallingObj(),chain.getCallingMethod(),chain.getArgs()))     throw e;
  }
}
