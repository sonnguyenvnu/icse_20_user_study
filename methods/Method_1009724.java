public void hide(int index,boolean save){
  if (submissions != null) {
    savedSubmission=submissions.get(index);
    submissions.remove(index);
    savedIndex=index;
    writeToMemoryNoStorage();
  }
}
