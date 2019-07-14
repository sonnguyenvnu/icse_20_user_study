public static Action newAction(String name,ImageIcon icon,boolean enable,ActionListener listener){
  Action action=newAction(name,enable,listener);
  action.putValue(Action.SMALL_ICON,icon);
  return action;
}
