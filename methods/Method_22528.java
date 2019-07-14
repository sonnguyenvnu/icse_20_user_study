private boolean setFlag(String flagFilename,boolean flag){
  if (flag) {
    try {
      new File(getFolder(),flagFilename).createNewFile();
      return true;
    }
 catch (    IOException e) {
      return false;
    }
  }
 else {
    return new File(getFolder(),flagFilename).delete();
  }
}
