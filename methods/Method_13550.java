private static void checkDataIdFileExtension(String[] dataIdArray){
  StringBuilder stringBuilder=new StringBuilder();
  for (int i=0; i < dataIdArray.length; i++) {
    boolean isLegal=false;
    for (    String fileExtension : SUPPORT_FILE_EXTENSION) {
      if (dataIdArray[i].indexOf(fileExtension) > 0) {
        isLegal=true;
        break;
      }
    }
    if (!isLegal) {
      stringBuilder.append(dataIdArray[i] + ",");
    }
  }
  if (stringBuilder.length() > 0) {
    String result=stringBuilder.substring(0,stringBuilder.length() - 1);
    throw new IllegalStateException(String.format("[%s] must contains file extension with properties|yaml|yml",result));
  }
}
