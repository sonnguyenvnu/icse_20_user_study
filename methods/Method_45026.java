public void onFindAllMenu(){
  try {
    if (findAllBox == null)     findAllBox=new FindAllBox(this);
    findAllBox.showFindBox();
  }
 catch (  Exception e) {
    Luyten.showExceptionDialog("Exception!",e);
  }
}
