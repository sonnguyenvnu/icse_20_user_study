public void show(ModelGenerationPlan plan){
  messageViewDelimiter();
  for (  TemplateModule generator : plan.getGenerators()) {
    Collection<TemplateMappingPriorityRule> rules=generator.getPriorities();
    for (    TemplateMappingPriorityRule r : rules) {
      Message msg=new Message(MessageKind.INFORMATION,r.toString());
      msg.setHintObject(generator.getModuleReference());
      messagesView.add(msg);
    }
  }
  messageViewDelimiter();
  if (plan instanceof GenerationPlan) {
    GenerationPlan planImpl=(GenerationPlan)plan;
    if (planImpl.hasIgnoredPriorityRules()) {
      printPlanConflicts(planImpl.getIgnoredPriorityRules(),"Ignored mapping priority rules:");
    }
    if (planImpl.hasConflictingPriorityRules()) {
      printPlanConflicts(planImpl.getConflicts(),"Conflicting mapping priority rules encountered:");
    }
  }
  console.addText("---------------------  mappings partitioning  -----------------------------------\n\n");
  int stepCount=1;
  for (  ModelGenerationPlan.Step step : plan.getSteps()) {
    console.addText(" [ " + stepCount++ + " ]\n");
    printStep(step,0);
    console.addText("\n");
  }
  console.addText("---------------------------------------------------------------------------------\n");
}
