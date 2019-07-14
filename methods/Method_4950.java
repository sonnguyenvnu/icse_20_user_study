@Nullable @VisibleForTesting Metadata decode(String metadata){
  String name=null;
  String url=null;
  int index=0;
  Matcher matcher=METADATA_ELEMENT.matcher(metadata);
  while (matcher.find(index)) {
    String key=Util.toLowerInvariant(matcher.group(1));
    String value=matcher.group(2);
switch (key) {
case STREAM_KEY_NAME:
      name=value;
    break;
case STREAM_KEY_URL:
  url=value;
break;
default :
Log.w(TAG,"Unrecognized ICY tag: " + name);
break;
}
index=matcher.end();
}
return (name != null || url != null) ? new Metadata(new IcyInfo(name,url)) : null;
}
