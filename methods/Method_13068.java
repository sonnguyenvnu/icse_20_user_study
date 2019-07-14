public static Action newAction(ImageIcon icon,boolean enable,ActionListener listener){
  Action action=newAction(null,icon,enable,listener);
  action.putValue(Action.SMALL_ICON,icon);
  return action;
}
