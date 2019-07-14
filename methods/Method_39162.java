/** 
 * Sets the  {@link jodd.madvoc.ActionRequest#setNextActionPath(String) next action request} for the chain.
 */
@Override public void render(final ActionRequest actionRequest,final Object resultValue){
  final Chain chainResult;
  if (resultValue == null) {
    chainResult=Chain.to(StringPool.EMPTY);
  }
 else {
    if (resultValue instanceof String) {
      chainResult=Chain.to((String)resultValue);
    }
 else {
      chainResult=(Chain)resultValue;
    }
  }
  final String resultBasePath=actionRequest.getActionRuntime().getResultBasePath();
  final String resultPath=resultMapper.resolveResultPathString(resultBasePath,chainResult.path());
  actionRequest.setNextActionPath(resultPath);
}
