private void showPopup(View v,final AppDataParcelable rowItem){
  v.setOnClickListener(view -> {
    PopupMenu popupMenu=new PopupMenu(app.getActivity(),view);
    popupMenu.setOnMenuItemClickListener(item -> {
      int colorAccent=themedActivity.getAccent();
switch (item.getItemId()) {
case R.id.open:
        Intent i1=app.getActivity().getPackageManager().getLaunchIntentForPackage(rowItem.packageName);
      if (i1 != null)       app.startActivity(i1);
 else       Toast.makeText(app.getActivity(),app.getString(R.string.not_allowed),Toast.LENGTH_LONG).show();
    return true;
case R.id.share:
  ArrayList<File> arrayList2=new ArrayList<File>();
arrayList2.add(new File(rowItem.path));
themedActivity.getColorPreference();
FileUtils.shareFiles(arrayList2,app.getActivity(),utilsProvider.getAppTheme(),colorAccent);
return true;
case R.id.unins:
final HybridFileParcelable f1=new HybridFileParcelable(rowItem.path);
f1.setMode(OpenMode.ROOT);
if ((Integer.valueOf(rowItem.data.substring(0,rowItem.data.indexOf("_"))) & ApplicationInfo.FLAG_SYSTEM) != 0) {
if (app.Sp.getBoolean(PreferencesConstants.PREFERENCE_ROOTMODE,false)) {
MaterialDialog.Builder builder1=new MaterialDialog.Builder(app.getActivity());
builder1.theme(utilsProvider.getAppTheme().getMaterialDialogTheme()).content(app.getString(R.string.unin_system_apk)).title(app.getString(R.string.warning)).negativeColor(colorAccent).positiveColor(colorAccent).negativeText(app.getString(R.string.no)).positiveText(app.getString(R.string.yes)).onNegative(((dialog,which) -> dialog.cancel())).onPositive(((dialog,which) -> {
ArrayList<HybridFileParcelable> files=new ArrayList<>();
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
String parent=f1.getParent(context);
if (!parent.equals("app") && !parent.equals("priv-app")) {
HybridFileParcelable baseFile=new HybridFileParcelable(f1.getParent(context));
baseFile.setMode(OpenMode.ROOT);
files.add(baseFile);
}
 else files.add(f1);
}
 else {
files.add(f1);
}
new DeleteTask(app.getActivity()).execute((files));
}
)).build().show();
}
 else {
Toast.makeText(app.getActivity(),app.getString(R.string.enablerootmde),Toast.LENGTH_SHORT).show();
}
}
 else {
app.unin(rowItem.packageName);
}
return true;
case R.id.play:
Intent intent1=new Intent(Intent.ACTION_VIEW);
try {
intent1.setData(Uri.parse(String.format("market://details?id=%s",rowItem.packageName)));
app.startActivity(intent1);
}
 catch (ActivityNotFoundException ifPlayStoreNotInstalled) {
intent1.setData(Uri.parse(String.format("https://play.google.com/store/apps/details?id=%s",rowItem.packageName)));
app.startActivity(intent1);
}
return true;
case R.id.properties:
app.startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,Uri.parse(String.format("package:%s",rowItem.packageName))));
return true;
case R.id.backup:
Toast.makeText(app.getActivity(),app.getString(R.string.copyingapk) + Environment.getExternalStorageDirectory().getPath() + "/app_backup",Toast.LENGTH_LONG).show();
File f=new File(rowItem.path);
ArrayList<HybridFileParcelable> ab=new ArrayList<>();
File dst=new File(Environment.getExternalStorageDirectory().getPath() + "/app_backup");
if (!dst.exists() || !dst.isDirectory()) dst.mkdirs();
Intent intent=new Intent(app.getActivity(),CopyService.class);
HybridFileParcelable baseFile=RootHelper.generateBaseFile(f,true);
baseFile.setName(rowItem.label + "_" + rowItem.packageName.substring(rowItem.packageName.indexOf("_") + 1) + ".apk");
ab.add(baseFile);
intent.putParcelableArrayListExtra(CopyService.TAG_COPY_SOURCES,ab);
intent.putExtra(CopyService.TAG_COPY_TARGET,dst.getPath());
intent.putExtra(CopyService.TAG_COPY_OPEN_MODE,0);
ServiceWatcherUtil.runService(app.getActivity(),intent);
return true;
}
return false;
}
);
popupMenu.inflate(R.menu.app_options);
popupMenu.show();
}
);
}
