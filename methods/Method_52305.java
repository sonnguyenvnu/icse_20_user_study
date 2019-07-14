private static String[] imagesOf(List<? extends Node> nodes){
  String[] imageArray=new String[nodes.size()];
  for (int i=0; i < nodes.size(); i++) {
    imageArray[i]=nodes.get(i).getImage();
  }
  return imageArray;
}
