public void onFileLoadEnded(File file,boolean isSuccess){
  try {
    if (file != null && isSuccess) {
      this.setTitle(TITLE + " - " + file.getName());
    }
 else {
      this.setTitle(TITLE);
    }
  }
 catch (  Exception e) {
    Luyten.showExceptionDialog("Exception!",e);
  }
}
