/** 
 * ????
 * @param file  file
 * @param email email
 * @return fileUrl
 */
public static String upload(MultipartFile file,String email){
  String savePath="";
  String filename="";
  if (file != null && !file.isEmpty()) {
    String fileName=file.getOriginalFilename();
    filename=FileUtil.getNewFileName(fileName,email);
    String os=System.getProperty("os.name").toLowerCase();
    try {
      File newFile=new File(savePath);
      if (!newFile.exists()) {
        boolean result=newFile.mkdirs();
        System.out.println(result);
      }
      savePath=savePath + filename;
      FileOutputStream out=new FileOutputStream(savePath);
      out.write(file.getBytes());
      out.flush();
      out.close();
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  return filename;
}
