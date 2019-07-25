public void filter(InterceptorChain chain){
  try {
    if (logBeforeInvoke)     LOG.debugf("[beforeInvoke] Obj = %s , Method = %s , args = %s",toString(chain.getCallingObj()),chain.getCallingMethod(),str(chain.getArgs()));
    chain.doChain();
  }
 catch (  Exception e) {
    if (logWhenException)     LOG.debugf("[whenException] Obj = %s , Throwable = %s , Method = %s , args = %s",toString(chain.getCallingObj()),e,chain.getCallingMethod(),str(chain.getArgs()));
    throw Lang.wrapThrow(e);
  }
catch (  Throwable e) {
    if (logWhenError)     LOG.debugf("[whenError] Obj = %s , Throwable = %s , Method = %s , args = %s",toString(chain.getCallingObj()),e,chain.getCallingMethod(),str(chain.getArgs()));
    throw Lang.wrapThrow(e);
  }
 finally {
    if (logAfterInvoke)     LOG.debugf("[afterInvoke] Obj = %s , Return = %s , Method = %s , args = %s",toString(chain.getCallingObj()),chain.getReturn(),chain.getCallingMethod(),str(chain.getArgs()));
  }
}
