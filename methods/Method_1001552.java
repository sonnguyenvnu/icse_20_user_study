public static Command create(ISkinSimple skinParam){
  return new CommandCreoleUrl(skinParam,"^(" + UrlBuilder.getRegexp() + ")");
}
