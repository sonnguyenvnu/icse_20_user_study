@GetMapping("/aliyun/download2") public String download2() throws IOException {
  return "http://" + OssConstant.ALIYUN_OSS_BUCKET_NAME + "." + OssConstant.ALIYUN_OSS_ENDPOINT + "/file.png";
}
