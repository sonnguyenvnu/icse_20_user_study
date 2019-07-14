private static void inputWhitelist(final Whitelist whitelist){
  whitelist.addTags("span","hr","kbd","samp","tt","del","s","strike","u","details","summary").addAttributes("iframe","src","width","height","border","marginwidth","marginheight").addAttributes("audio","controls","src").addAttributes("video","controls","src","width","height").addAttributes("source","src","media","type").addAttributes("object","width","height","data","type").addAttributes("param","name","value").addAttributes("input","type","disabled","checked").addAttributes("embed","src","type","width","height","wmode","allowNetworking").addAttributes("code","class").addAttributes("span","class").addAttributes("p","align");
  for (int i=1; i <= 6; i++) {
    whitelist.addAttributes("h" + i,"align");
  }
}
