@Override public char[] GetSuffix(int len){
  String image=GetImage();
  return image.substring(image.length() - len,image.length()).toCharArray();
}
