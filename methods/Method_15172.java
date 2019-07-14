private String setCuttedPicturePath(){
  cuttedPicturePath=intent.getStringExtra(INTENT_CUTTED_PICTURE_PATH);
  if (StringUtil.isFilePath(cuttedPicturePath) == false) {
    cuttedPicturePath=DataKeeper.fileRootPath + DataKeeper.imagePath;
  }
  cuttedPictureName=intent.getStringExtra(INTENT_CUTTED_PICTURE_NAME);
  if (StringUtil.isFilePath(cuttedPictureName) == false) {
    cuttedPictureName="photo" + System.currentTimeMillis();
  }
  return cuttedPicturePath;
}
