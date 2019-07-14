protected final Option configOption(){
  Option opt=new Option("c",true,"config");
  opt.setType(String.class);
  opt.setRequired(false);
  return opt;
}
