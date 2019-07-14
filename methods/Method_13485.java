@GetMapping("/download") public String download(){
  try {
    OSSObject ossObject=ossClient.getObject(OssApplication.BUCKET_NAME,"oss-test.json");
    return "download success, content: " + IOUtils.readStreamAsString(ossObject.getObjectContent(),CharEncoding.UTF_8);
  }
 catch (  Exception e) {
    e.printStackTrace();
    return "download fail: " + e.getMessage();
  }
}
