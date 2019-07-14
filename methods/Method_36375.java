private boolean notInWhiteNameList(String beanName){
  for (  String whiteName : whiteNameList) {
    if (whiteName.equals(beanName)) {
      return false;
    }
  }
  return true;
}
