public static String[] getProjection(){
  return new String[]{MediaStore.Files.FileColumns.PARENT,MediaStore.Images.Media.BUCKET_DISPLAY_NAME,"count(*)",MediaStore.Images.Media.DATA,"max(" + MediaStore.Images.Media.DATE_MODIFIED + ")"};
}
