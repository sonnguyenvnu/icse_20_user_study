public boolean registeApplication(ApplicationInfoRequest applicationInfoRequest){
  final String json=applicationInfoRequest.toJson();
  String result=httpPost(MeshEndpoint.CONFIGS,json);
  if (!StringUtils.equals(result,errorMessage)) {
    final ApplicationInfoResult parse=JSON.parseObject(result,ApplicationInfoResult.class);
    if (parse.isSuccess()) {
      return true;
    }
    return false;
  }
 else {
    return false;
  }
}
