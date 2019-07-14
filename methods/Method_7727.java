public ThemeDescriptionDelegate setDelegateDisabled(){
  ThemeDescriptionDelegate oldDelegate=delegate;
  delegate=null;
  return oldDelegate;
}
