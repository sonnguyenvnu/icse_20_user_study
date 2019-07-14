public void notifyCodeFolderChanged(){
  Messages.log("PPS: snotified code folder changed");
  codeFolderChanged.set(true);
  notifySketchChanged();
}
