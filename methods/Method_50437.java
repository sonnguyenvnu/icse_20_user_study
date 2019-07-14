public void logo(){
  String bannerText=buildBannerText();
  if (LOGGER.isInfoEnabled()) {
    LOGGER.info(bannerText);
  }
 else {
    System.out.print(bannerText);
  }
}
