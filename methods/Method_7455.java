public CallingCodeInfo findCallingCodeInfo(String str){
  CallingCodeInfo res=null;
  for (int i=0; i < 3; i++) {
    if (i < str.length()) {
      res=callingCodeInfo(str.substring(0,i + 1));
      if (res != null) {
        break;
      }
    }
 else {
      break;
    }
  }
  return res;
}
