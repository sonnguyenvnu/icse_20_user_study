/** 
 * @see junit.framework.TestListener#addError(Test,Throwable)
 */
public void addError(Test test,Throwable e){
  getWriter().print("E");
}
