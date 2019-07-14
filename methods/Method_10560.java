@OnClick({R.id.btn_create_folder,R.id.btn_zip,R.id.btn_upzip,R.id.btn_zip_delete_dir}) public void onViewClicked(View view){
switch (view.getId()) {
case R.id.btn_create_folder:
    fileDir=new File(zipParentPath);
  fileTempDir=new File(zipTempDeletePath);
if (!fileDir.exists()) {
  fileDir.mkdirs();
}
if (!fileTempDir.exists()) {
fileTempDir.mkdirs();
}
try {
File file=File.createTempFile("????????",".txt",fileDir);
File file1=File.createTempFile("?????o(???)o",".txt",fileTempDir);
}
 catch (IOException e) {
e.printStackTrace();
}
mTvState.setText("???? ????,????????RxTool?(???)");
break;
case R.id.btn_zip:
fileZip=new File(zipPath);
if (fileZip.exists()) {
RxFileTool.deleteFile(fileZip);
Logger.d("????????????");
}
if (fileDir != null) {
if (fileDir.exists()) {
String result=RxZipTool.zipEncrypt(fileDir.getAbsolutePath(),fileZip.getAbsolutePath(),true,"123456");
mTvState.setText("???????,??" + result);
}
 else {
RxToast.error("????????");
}
}
 else {
RxToast.error("????????");
}
break;
case R.id.btn_upzip:
List<File> zipFiles=RxZipTool.unzipFileByKeyword(fileZip,unZipDirFile,"123456");
String str="??????(*?*)\n";
if (zipFiles != null) {
for (File zipFile : zipFiles) {
str+=zipFile.getAbsolutePath() + "\n\n";
}
}
mTvState.setText(str);
break;
case R.id.btn_zip_delete_dir:
if (RxZipTool.removeDirFromZipArchive(zipPath,"RxTool" + File.separator + "RxTempTool")) {
mTvState.setText("RxTempTool ????");
}
 else {
mTvState.setText("RxTempTool ????");
}
break;
default :
break;
}
}
