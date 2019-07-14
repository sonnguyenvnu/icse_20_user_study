@Override public SuccessEnum save(AppUser appUser){
  try {
    appUserDao.save(appUser);
    return SuccessEnum.SUCCESS;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return SuccessEnum.FAIL;
  }
}
