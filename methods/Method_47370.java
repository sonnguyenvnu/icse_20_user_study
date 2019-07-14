public void extractFile(File file){
  int mode=checkFolder(file.getParentFile(),mainActivity);
  if (mode == 2) {
    mainActivity.oppathe=(file.getPath());
    mainActivity.operation=DataUtils.EXTRACT;
  }
 else   if (mode == 1) {
    Decompressor decompressor=CompressedHelper.getCompressorInstance(mainActivity,file);
    decompressor.decompress(file.getPath());
  }
 else   Toast.makeText(mainActivity,R.string.not_allowed,Toast.LENGTH_SHORT).show();
}
