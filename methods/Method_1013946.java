/** 
 * The Method is called by the RuleEngine to execute a  {@link Rule} {@link Action}.
 * @param context contains action input values and snapshot of all module output values. The output ids are definedin form: ModuleId.outputId
 * @return values map of values which must be set to outputs of the {@link Action}.
 */
@Override public Map<String,Object> execute(Map<String,Object> context){
  final Integer inputValue=(Integer)context.get(INPUT_NAME);
  System.out.println("Type UID: " + module.getTypeUID() + ", rule ID " + ruleUID + ", Input value: " + inputValue);
  return null;
}
