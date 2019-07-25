public static LightsTriggerType initialize(){
  Output state=new Output(StateConditionType.INPUT_CURRENT_STATE,String.class.getName(),"State","Indicates the state of Lights",null,null,null);
  List<Output> output=new ArrayList<Output>();
  output.add(state);
  return new LightsTriggerType(output);
}
