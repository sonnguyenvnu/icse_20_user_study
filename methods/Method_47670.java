@Override public int getTheme(){
  HabitsApplicationComponent component=((HabitsApplication)getContext().getApplicationContext()).getComponent();
  if (component.getPreferences().getTheme() == THEME_LIGHT)   return R.style.DialogWithTitle;
 else   return R.style.DarkDialogWithTitle;
}
