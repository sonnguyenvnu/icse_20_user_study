public static Action newAction(String name,ImageIcon icon,boolean enable,String shortDescription,ActionListener listener){
  Action action=newAction(name,icon,enable,listener);
  action.putValue(Action.SHORT_DESCRIPTION,shortDescription);
  return action;
}
