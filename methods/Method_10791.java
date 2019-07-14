/** 
 * ??????????bitmap url - ?????????????,??: <p/> A.????: url="http://blog.foreverlove.us/girl2.png" ; <p/> B.????:url="file://mnt/sdcard/photo/image.png"; <p/> C.??????? ,png, jpg,bmp,gif??
 * @param url
 * @return
 */
public static Bitmap GetLocalOrNetBitmap(String url){
  Bitmap bitmap=null;
  InputStream in=null;
  BufferedOutputStream out=null;
  try {
    in=new BufferedInputStream(new URL(url).openStream(),1024);
    final ByteArrayOutputStream dataStream=new ByteArrayOutputStream();
    out=new BufferedOutputStream(dataStream,1024);
    copy(in,out);
    out.flush();
    byte[] data=dataStream.toByteArray();
    bitmap=BitmapFactory.decodeByteArray(data,0,data.length);
    data=null;
    return bitmap;
  }
 catch (  IOException e) {
    e.printStackTrace();
    return null;
  }
}
