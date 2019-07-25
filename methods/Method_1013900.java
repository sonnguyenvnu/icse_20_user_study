/** 
 * This method register the Rule to start working. This is the final step of initialization process where triggers received  {@link TriggerHandlerCallback}s object and starts to notify the rule engine when they are triggered. After activating all triggers the rule goes into IDLE state.
 * @param rule an initialized rule which has to starts tracking the triggers.
 */
private void register(WrappedRule rule){
  final String ruleUID=rule.getUID();
  TriggerHandlerCallback thCallback=getTriggerHandlerCallback(ruleUID);
  rule.getTriggers().forEach(trigger -> {
    TriggerHandler triggerHandler=trigger.getModuleHandler();
    if (triggerHandler != null) {
      triggerHandler.setCallback(thCallback);
    }
  }
);
  rule.getConditions().forEach(condition -> {
    ConditionHandler conditionHandler=condition.getModuleHandler();
    if (conditionHandler != null) {
      conditionHandler.setCallback(moduleHandlerCallback);
    }
  }
);
  rule.getActions().forEach(action -> {
    ActionHandler actionHandler=action.getModuleHandler();
    if (actionHandler != null) {
      actionHandler.setCallback(moduleHandlerCallback);
    }
  }
);
}
