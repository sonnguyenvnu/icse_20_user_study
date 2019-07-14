/** 
 * @see junit.framework.TestListener#addFailure(Test,AssertionFailedError)
 */
public void addFailure(Test test,AssertionFailedError t){
  getWriter().print("F");
}
