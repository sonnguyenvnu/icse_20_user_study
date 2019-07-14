private static String base64ToBase64Url(String base64){
  return base64.replace('+','-').replace('/','_');
}
