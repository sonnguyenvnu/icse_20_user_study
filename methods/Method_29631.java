public void sendRedisConfigTemplateChangeEmail(AppUser appUser,InstanceConfig instanceConfig,SuccessEnum successEnum,RedisConfigTemplateChangeEnum redisConfigTemplateChangeEnum){
  String mailTitle="?CacheCloud?-Redis????????";
  String mailContent=String.format("%s ?Redis???? ???%s,?????%s,???(key=%s,value=%s,???%s)",appUser.getChName(),redisConfigTemplateChangeEnum.getInfo(),successEnum.info(),instanceConfig.getConfigKey(),instanceConfig.getConfigValue(),instanceConfig.getStatusDesc());
  emailComponent.sendMail(mailTitle,mailContent.toString(),Arrays.asList(emailComponent.getAdminEmail().split(ConstUtils.COMMA)));
}
