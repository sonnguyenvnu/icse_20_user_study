private void colorChangeDialog(String colorPrefKey){
  final UserColorPreferences userColorPreferences=activity.getCurrentColorPreference();
  if (userColorPreferences != null) {
    @ColorInt int currentColor=0;
switch (colorPrefKey) {
case PreferencesConstants.PREFERENCE_SKIN:
      currentColor=userColorPreferences.primaryFirstTab;
    break;
case PreferencesConstants.PREFERENCE_SKIN_TWO:
  currentColor=userColorPreferences.primarySecondTab;
break;
case PreferencesConstants.PREFERENCE_ACCENT:
currentColor=userColorPreferences.accent;
break;
case PreferencesConstants.PREFERENCE_ICON_SKIN:
currentColor=userColorPreferences.iconSkin;
break;
}
ColorAdapter adapter=new ColorAdapter(getActivity(),ColorPreference.availableColors,currentColor,(selectedColor) -> {
@ColorInt int primaryFirst=userColorPreferences.primaryFirstTab, primarySecond=userColorPreferences.primarySecondTab, accent=userColorPreferences.accent, iconSkin=userColorPreferences.iconSkin;
switch (colorPrefKey) {
case PreferencesConstants.PREFERENCE_SKIN:
primaryFirst=selectedColor;
break;
case PreferencesConstants.PREFERENCE_SKIN_TWO:
primarySecond=selectedColor;
break;
case PreferencesConstants.PREFERENCE_ACCENT:
accent=selectedColor;
break;
case PreferencesConstants.PREFERENCE_ICON_SKIN:
iconSkin=selectedColor;
break;
}
activity.getColorPreference().saveColorPreferences(sharedPref,new UserColorPreferences(primaryFirst,primarySecond,accent,iconSkin));
if (dialog != null) dialog.dismiss();
invalidateEverything();
}
);
GridView v=(GridView)getActivity().getLayoutInflater().inflate(R.layout.dialog_grid,null);
v.setAdapter(adapter);
v.setOnItemClickListener(adapter);
int fab_skin=activity.getAccent();
dialog=new MaterialDialog.Builder(getActivity()).positiveText(R.string.cancel).title(R.string.choose_color).theme(activity.getAppTheme().getMaterialDialogTheme()).autoDismiss(true).positiveColor(fab_skin).neutralColor(fab_skin).neutralText(R.string.defualt).callback(new MaterialDialog.ButtonCallback(){
@Override public void onNeutral(MaterialDialog dialog){
super.onNeutral(dialog);
if (activity != null) activity.setRestartActivity();
activity.getColorPreference().saveColorPreferences(sharedPref,userColorPreferences);
invalidateEverything();
}
}
).customView(v,false).show();
}
}
