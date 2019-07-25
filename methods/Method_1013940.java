/** 
 * This method is used to configure the provided rules.
 * @param params provides external data, that is used for configuring the rules.
 * @param console provides the output of the command.
 */
private void settings(String[] params,Console console){
  if (params.length < 2) {
    console.println("Missing required parameters");
    return;
  }
  Configuration config=rulesProvider.rules.get(WelcomeHomeRulesProvider.AC_UID).getConfiguration();
  if (params[0] != null && (params[0].equalsIgnoreCase(TemperatureConditionType.OPERATOR_HEATING) || params[0].equalsIgnoreCase(TemperatureConditionType.OPERATOR_COOLING))) {
    config.put(AirConditionerRuleTemplate.CONFIG_OPERATION,params[0]);
  }
 else {
    console.println("Invalid parameter value of the parameter \"mode\". Should be \"" + TemperatureConditionType.OPERATOR_HEATING + "\" or \"" + TemperatureConditionType.OPERATOR_COOLING + "\"");
    return;
  }
  if (params[1] != null) {
    Integer temperature;
    try {
      temperature=new Integer(params[1]);
      config.put(AirConditionerRuleTemplate.CONFIG_TARGET_TEMPERATURE,temperature);
    }
 catch (    NumberFormatException e) {
      console.println("Invalid parameter value of the parameter \"temperature\". Should be number.");
      return;
    }
  }
  rulesProvider.update(WelcomeHomeRulesProvider.AC_UID,AirConditionerRuleTemplate.UID,config);
  console.println("SUCCESS");
}
