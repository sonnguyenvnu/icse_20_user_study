/** 
 * ?????,?????????????
 * @throws Throwable ??????????
 */
public void invoke() throws Throwable {
  if (invoked)   log.warnf("!! Calling Method more than once! Method --> %s",callingMethod.toString());
 else   invoked=true;
  this.returnValue=callingObj._aop_invoke(methodIndex,args);
}
