public static ImageData error(){
  final ImageDataSimple result=new ImageDataSimple();
  result.setStatus(503);
  return result;
}
