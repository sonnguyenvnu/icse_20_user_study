private void addShortcut(LayoutElementParcelable path){
  final Context ctx=getContext();
  if (!ShortcutManagerCompat.isRequestPinShortcutSupported(ctx)) {
    Toast.makeText(getActivity(),getString(R.string.addshortcut_not_supported_by_launcher),Toast.LENGTH_SHORT).show();
    return;
  }
  Intent shortcutIntent=new Intent(ctx,MainActivity.class);
  shortcutIntent.putExtra("path",path.desc);
  shortcutIntent.setAction(Intent.ACTION_MAIN);
  shortcutIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
  ShortcutInfoCompat info=new ShortcutInfoCompat.Builder(ctx,path.desc).setActivity(getMainActivity().getComponentName()).setIcon(IconCompat.createWithResource(ctx,R.mipmap.ic_launcher)).setIntent(shortcutIntent).setLongLabel(path.desc).setShortLabel(new File(path.desc).getName()).build();
  ShortcutManagerCompat.requestPinShortcut(ctx,info,null);
}
