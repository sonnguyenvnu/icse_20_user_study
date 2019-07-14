private void emitWarning(String type,String example){
  QAWarning warning=new QAWarning(type,null,QAWarning.Severity.WARNING,1);
  warning.setProperty("example_string",example);
  addIssue(warning);
}
