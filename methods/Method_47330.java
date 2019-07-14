private static Uri fileToContentUri(Context context,String path,boolean isDirectory,String volume){
  final String where=MediaStore.MediaColumns.DATA + " = ?";
  Uri baseUri;
  String[] projection;
  int mimeType=Icons.getTypeOfFile(path,isDirectory);
switch (mimeType) {
case Icons.IMAGE:
    baseUri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
  projection=new String[]{BaseColumns._ID};
break;
case Icons.VIDEO:
baseUri=MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
projection=new String[]{BaseColumns._ID};
break;
case Icons.AUDIO:
baseUri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
projection=new String[]{BaseColumns._ID};
break;
default :
baseUri=MediaStore.Files.getContentUri(volume);
projection=new String[]{BaseColumns._ID,MediaStore.Files.FileColumns.MEDIA_TYPE};
}
ContentResolver cr=context.getContentResolver();
Cursor c=cr.query(baseUri,projection,where,new String[]{path},null);
try {
if (c != null && c.moveToNext()) {
boolean isValid=false;
if (mimeType == Icons.IMAGE || mimeType == Icons.VIDEO || mimeType == Icons.AUDIO) {
isValid=true;
}
 else {
int type=c.getInt(c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE));
isValid=type != 0;
}
if (isValid) {
long id=c.getLong(c.getColumnIndexOrThrow(BaseColumns._ID));
return Uri.withAppendedPath(baseUri,String.valueOf(id));
}
}
}
  finally {
if (c != null) {
c.close();
}
}
return null;
}
