public static TemperatureConditionType initialize(){
  final ConfigDescriptionParameter temperature=ConfigDescriptionParameterBuilder.create(CONFIG_TEMPERATURE,Type.INTEGER).withRequired(true).withReadOnly(true).withMultiple(false).withLabel("Temperature").withDescription("Targeted room temperature").build();
  final ConfigDescriptionParameter operator=ConfigDescriptionParameterBuilder.create(CONFIG_OPERATOR,Type.TEXT).withRequired(true).withReadOnly(true).withMultiple(false).withLabel("Mode").withDescription("Heating/Cooling mode").build();
  final List<ConfigDescriptionParameter> config=new ArrayList<ConfigDescriptionParameter>();
  config.add(temperature);
  config.add(operator);
  Input currentTemperature=new Input(INPUT_CURRENT_TEMPERATURE,Integer.class.getName(),"Current Temperature","Current room temperature",null,true,null,null);
  List<Input> input=new ArrayList<Input>();
  input.add(currentTemperature);
  return new TemperatureConditionType(config,input);
}
