/** 
 * ?????????
 * @return ????
 */
public static String getModelPath(){
  File fileImagePath=null;
  try {
    String imageName="immqy_%s_%s.jpg";
    Random random=new Random();
    String filename=String.format(imageName,SimpleDateUtils.getNowTime(),"" + random.nextInt(1024));
    File mImageStoreDir=new File(Environment.getExternalStorageDirectory(),"/DCIM/IMMQY/");
    fileImagePath=new File(mImageStoreDir,filename);
    Logger.i("Test Path:" + fileImagePath.getPath());
  }
 catch (  Exception e) {
    e.printStackTrace();
    Logger.e("e=>" + e.getMessage());
  }
  return fileImagePath.getPath();
}
