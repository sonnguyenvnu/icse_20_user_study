public static Option shutdownPortOption(){
  Option opt=new Option("s",true,"shutdown port");
  opt.setType(String.class);
  opt.setRequired(false);
  return opt;
}
