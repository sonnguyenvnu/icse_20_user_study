private void handleError(RuleContext ctx,Report errorReport,RuntimeException pmde){
  pmde.printStackTrace();
  project.log(pmde.toString(),Project.MSG_VERBOSE);
  Throwable cause=pmde.getCause();
  if (cause != null) {
    try (StringWriter strWriter=new StringWriter();PrintWriter printWriter=new PrintWriter(strWriter)){
      cause.printStackTrace(printWriter);
      project.log(strWriter.toString(),Project.MSG_VERBOSE);
    }
 catch (    IOException e) {
      project.log("Error while closing stream",e,Project.MSG_ERR);
    }
    if (StringUtils.isNotBlank(cause.getMessage())) {
      project.log(cause.getMessage(),Project.MSG_VERBOSE);
    }
  }
  if (failOnError) {
    throw new BuildException(pmde);
  }
  errorReport.addError(new Report.ProcessingError(pmde,ctx.getSourceCodeFilename()));
}
