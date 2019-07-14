/** 
 * @param text        ?????????
 * @param imageView   ??????ImageView
 * @param centerPhoto ????????
 */
public static void showThreadImage(final Activity mContext,final String text,final ImageView imageView,final int centerPhoto){
  String preContent=SPUtils.getString("share_code_content","");
  if (text.equals(preContent)) {
    String preFilePath=SPUtils.getString("share_code_filePath","");
    imageView.setImageBitmap(BitmapFactory.decodeFile(preFilePath));
  }
 else {
    SPUtils.putString("share_code_content",text);
    final String filePath=getFileRoot(mContext) + File.separator + "qr_" + System.currentTimeMillis() + ".jpg";
    SPUtils.putString("share_code_filePath",filePath);
    new Thread(new Runnable(){
      @Override public void run(){
        boolean success=QRCodeUtil.createQRImage(text,800,800,BitmapFactory.decodeResource(mContext.getResources(),centerPhoto),filePath);
        if (success) {
          mContext.runOnUiThread(new Runnable(){
            @Override public void run(){
              imageView.setImageBitmap(BitmapFactory.decodeFile(filePath));
            }
          }
);
        }
      }
    }
).start();
  }
}
