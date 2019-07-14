@Keep public static Uri wrapperUri(LoadedPlugin loadedPlugin,Uri pluginUri){
  String pkg=loadedPlugin.getPackageName();
  String pluginUriString=Uri.encode(pluginUri.toString());
  StringBuilder builder=new StringBuilder(RemoteContentProvider.getUri(loadedPlugin.getHostContext()));
  builder.append("/?plugin=" + loadedPlugin.getLocation());
  builder.append("&pkg=" + pkg);
  builder.append("&uri=" + pluginUriString);
  Uri wrapperUri=Uri.parse(builder.toString());
  return wrapperUri;
}
