protected final Option portOption(){
  Option opt=new Option("p",true,"port");
  opt.setType(Number.class);
  opt.setRequired(false);
  return opt;
}
