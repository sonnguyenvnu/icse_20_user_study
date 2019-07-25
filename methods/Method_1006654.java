@Bean public ImageFilePath img(){
  AWSFilePathUtils awsFilePathUtils=new AWSFilePathUtils();
  awsFilePathUtils.setBasePath(contentUrl);
  return awsFilePathUtils;
}
