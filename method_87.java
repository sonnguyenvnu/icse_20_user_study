@Override public synchronized AlertStreamEvent _XXXXX_(AlertStreamEvent event){
  Preconditions.checkArgument(this.policyDefinitionRepository.containsKey(event.getPolicyId()),"Unknown policyId " + event.getPolicyId());
  PolicyDefinition policyDefinition=this.policyDefinitionRepository.get(event.getPolicyId());
  StringWriter bodyWriter=new StringWriter();
  StringWriter subjectWriter=new StringWriter();
  try {
    VelocityContext alertContext=buildAlertContext(policyDefinition,event);
    Template template=engine.getTemplate(getAlertBodyTemplateName(event.getPolicyId()));
    template.merge(alertContext,bodyWriter);
    event.setBody(bodyWriter.toString());
    template=engine.getTemplate(getAlertSubjectTemplateName(event.getPolicyId()));
    template.merge(alertContext,subjectWriter);
    event.setSubject(subjectWriter.toString());
  }
  finally {
    try {
      bodyWriter.close();
    }
 catch (    IOException e) {
      LOG.warn(e.getMessage(),e);
    }
    try {
      subjectWriter.close();
    }
 catch (    IOException e) {
      LOG.warn(e.getMessage(),e);
    }
  }
  return event;
}