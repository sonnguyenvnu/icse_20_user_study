/** 
 * @see junit.framework.TestListener#startTest(Test)
 */
public void startTest(Test test){
  getWriter().print(".");
  if (fColumn++ >= 40) {
    getWriter().println();
    fColumn=0;
  }
}
