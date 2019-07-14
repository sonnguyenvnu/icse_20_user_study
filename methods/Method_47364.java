public void guideDialogForLEXA(String path){
  final MaterialDialog.Builder x=new MaterialDialog.Builder(mainActivity);
  x.theme(mainActivity.getAppTheme().getMaterialDialogTheme());
  x.title(R.string.needsaccess);
  LayoutInflater layoutInflater=(LayoutInflater)mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  View view=layoutInflater.inflate(R.layout.lexadrawer,null);
  x.customView(view,true);
  TextView textView=view.findViewById(R.id.description);
  textView.setText(mainActivity.getString(R.string.needsaccesssummary) + path + mainActivity.getString(R.string.needsaccesssummary1));
  ((ImageView)view.findViewById(R.id.icon)).setImageResource(R.drawable.sd_operate_step);
  x.positiveText(R.string.open).negativeText(R.string.cancel).positiveColor(accentColor).negativeColor(accentColor).onPositive((dialog,which) -> triggerStorageAccessFramework()).onNegative((dialog,which) -> Toast.makeText(mainActivity,R.string.error,Toast.LENGTH_SHORT).show());
  final MaterialDialog y=x.build();
  y.show();
}
