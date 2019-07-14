/** 
 * Writes uri stream from external application to the specified path
 */
public static final void writeUriToStorage(@NonNull final MainActivity mainActivity,@NonNull final ArrayList<Uri> uris,@NonNull final ContentResolver contentResolver,@NonNull final String currentPath){
  AppConfig.runInParallel(new AppConfig.CustomAsyncCallbacks<Void,List<String>>(null){
    @Override public List<String> doInBackground(){
      List<String> retval=new ArrayList<>();
      for (      Uri uri : uris) {
        BufferedInputStream bufferedInputStream=null;
        try {
          bufferedInputStream=new BufferedInputStream(contentResolver.openInputStream(uri));
        }
 catch (        FileNotFoundException e) {
          e.printStackTrace();
        }
        BufferedOutputStream bufferedOutputStream=null;
        try {
          DocumentFile documentFile=DocumentFile.fromSingleUri(mainActivity,uri);
          String filename=documentFile.getName();
          if (filename == null) {
            filename=uri.getLastPathSegment();
            if (filename.contains("/"))             filename=filename.substring(filename.lastIndexOf('/') + 1);
          }
          String finalFilePath=currentPath + "/" + filename;
          DataUtils dataUtils=DataUtils.getInstance();
          HybridFile hFile=new HybridFile(OpenMode.UNKNOWN,currentPath);
          hFile.generateMode(mainActivity);
switch (hFile.getMode()) {
case FILE:
case ROOT:
            File targetFile=new File(finalFilePath);
          if (!FileUtil.isWritableNormalOrSaf(targetFile.getParentFile(),mainActivity.getApplicationContext())) {
            AppConfig.toast(mainActivity,mainActivity.getResources().getString(R.string.not_allowed));
            return null;
          }
        DocumentFile targetDocumentFile=getDocumentFile(targetFile,false,mainActivity.getApplicationContext());
      if (targetDocumentFile == null)       targetDocumentFile=DocumentFile.fromFile(targetFile);
    if (targetDocumentFile.exists() && targetDocumentFile.length() > 0) {
      AppConfig.toast(mainActivity,mainActivity.getString(R.string.cannot_overwrite));
      return null;
    }
  bufferedOutputStream=new BufferedOutputStream(contentResolver.openOutputStream(targetDocumentFile.getUri()));
retval.add(targetFile.getPath());
break;
case SMB:
SmbFile targetSmbFile=new SmbFile(finalFilePath);
if (targetSmbFile.exists()) {
AppConfig.toast(mainActivity,mainActivity.getString(R.string.cannot_overwrite));
return null;
}
 else {
OutputStream outputStream=targetSmbFile.getOutputStream();
bufferedOutputStream=new BufferedOutputStream(outputStream);
retval.add(mainActivity.mainActivityHelper.parseSmbPath(targetSmbFile.getPath()));
}
break;
case SFTP:
AppConfig.toast(mainActivity,mainActivity.getString(R.string.not_allowed));
return null;
case DROPBOX:
CloudStorage cloudStorageDropbox=dataUtils.getAccount(OpenMode.DROPBOX);
String path=CloudUtil.stripPath(OpenMode.DROPBOX,finalFilePath);
cloudStorageDropbox.upload(path,bufferedInputStream,documentFile.length(),true);
retval.add(path);
break;
case BOX:
CloudStorage cloudStorageBox=dataUtils.getAccount(OpenMode.BOX);
path=CloudUtil.stripPath(OpenMode.BOX,finalFilePath);
cloudStorageBox.upload(path,bufferedInputStream,documentFile.length(),true);
retval.add(path);
break;
case ONEDRIVE:
CloudStorage cloudStorageOneDrive=dataUtils.getAccount(OpenMode.ONEDRIVE);
path=CloudUtil.stripPath(OpenMode.ONEDRIVE,finalFilePath);
cloudStorageOneDrive.upload(path,bufferedInputStream,documentFile.length(),true);
retval.add(path);
break;
case GDRIVE:
CloudStorage cloudStorageGDrive=dataUtils.getAccount(OpenMode.GDRIVE);
path=CloudUtil.stripPath(OpenMode.GDRIVE,finalFilePath);
cloudStorageGDrive.upload(path,bufferedInputStream,documentFile.length(),true);
retval.add(path);
break;
case OTG:
DocumentFile documentTargetFile=OTGUtil.getDocumentFile(finalFilePath,mainActivity,true);
if (documentTargetFile.exists()) {
AppConfig.toast(mainActivity,mainActivity.getString(R.string.cannot_overwrite));
return null;
}
bufferedOutputStream=new BufferedOutputStream(contentResolver.openOutputStream(documentTargetFile.getUri()),GenericCopyUtil.DEFAULT_BUFFER_SIZE);
retval.add(documentTargetFile.getUri().getPath());
break;
default :
return null;
}
int count=0;
byte[] buffer=new byte[GenericCopyUtil.DEFAULT_BUFFER_SIZE];
while (count != -1) {
count=bufferedInputStream.read(buffer);
if (count != -1) {
bufferedOutputStream.write(buffer,0,count);
}
}
bufferedOutputStream.flush();
}
 catch (FileNotFoundException e) {
e.printStackTrace();
}
catch (MalformedURLException e) {
e.printStackTrace();
}
catch (IOException e) {
e.printStackTrace();
}
 finally {
try {
if (bufferedInputStream != null) {
bufferedInputStream.close();
}
if (bufferedOutputStream != null) {
bufferedOutputStream.close();
}
}
 catch (IOException e) {
e.printStackTrace();
}
}
}
return (retval.size() > 0) ? retval : null;
}
@Override public void onPostExecute(List<String> result){
if (result != null) {
List<String> paths=(List<String>)result;
if (paths.size() == 1) {
Toast.makeText(mainActivity,mainActivity.getString(R.string.saved_single_file,paths.get(0)),Toast.LENGTH_LONG).show();
}
 else {
Toast.makeText(mainActivity,mainActivity.getString(R.string.saved_multi_files,paths.size()),Toast.LENGTH_LONG).show();
}
}
}
}
);
}
