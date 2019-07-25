public static TriggerType initialize(){
  List<Output> output=new ArrayList<Output>();
  Output state=new Output(StateConditionType.INPUT_CURRENT_STATE,String.class.getName(),"State","Indicates if the Air Conditioner is switched On or Off.",null,null,null);
  Output temperature=new Output(TemperatureConditionType.INPUT_CURRENT_TEMPERATURE,Integer.class.getName(),"Temperature","Indicates the current room temperature",null,null,null);
  output.add(state);
  output.add(temperature);
  return new AirConditionerTriggerType(output);
}
