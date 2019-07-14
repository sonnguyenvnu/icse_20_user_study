public static Action newAction(String name,boolean enable,String shortDescription,ActionListener listener){
  Action action=newAction(name,enable,listener);
  action.putValue(Action.SHORT_DESCRIPTION,shortDescription);
  return action;
}
