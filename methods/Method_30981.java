@NonNull public static Intent makeInstallShortcut(int iconRes,int nameRes,@NonNull Class<?> intentClass,@NonNull Context context){
  return new Intent().setAction(ACTION_INSTALL_SHORTCUT).putExtra(Intent.EXTRA_SHORTCUT_INTENT,new Intent(context.getApplicationContext(),intentClass)).putExtra(Intent.EXTRA_SHORTCUT_NAME,context.getString(nameRes)).putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,Intent.ShortcutIconResource.fromContext(context,iconRes));
}
