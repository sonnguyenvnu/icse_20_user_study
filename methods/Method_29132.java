public String getStatusDesc(){
  AppStatusEnum appStatusEnum=AppStatusEnum.getByStatus(status);
  if (appStatusEnum != null) {
    return appStatusEnum.getInfo();
  }
  return "";
}
