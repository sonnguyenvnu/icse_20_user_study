@Override public QiniuConfig find(){
  Optional<QiniuConfig> qiniuConfig=qiNiuConfigRepository.findById(1L);
  if (qiniuConfig.isPresent()) {
    return qiniuConfig.get();
  }
 else {
    return new QiniuConfig();
  }
}
