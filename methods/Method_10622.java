public static void createBarCode(String content,int codeWidth,int codeHeight,ImageView iv_code){
  iv_code.setImageBitmap(createBarCode(content,codeWidth,codeHeight));
}
