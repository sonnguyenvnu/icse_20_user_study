/** 
 * @param iProblem - The IProblem which is being wrapped
 * @param tabIndex - The tab number to which the error belongs to
 * @param lineNumber - Line number(pde code) of the error
 * @param badCode - The code iProblem refers to.
 */
public static JavaProblem fromIProblem(IProblem iProblem,int tabIndex,int lineNumber,String badCode){
  int type=0;
  if (iProblem.isError()) {
    type=ERROR;
  }
 else   if (iProblem.isWarning()) {
    type=WARNING;
  }
  String message=ErrorMessageSimplifier.getSimplifiedErrorMessage(iProblem,badCode);
  return new JavaProblem(message,type,tabIndex,lineNumber);
}
