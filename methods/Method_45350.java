protected final Option envOption(){
  Option opt=new Option("e",true,"environment");
  opt.setType(String.class);
  opt.setRequired(false);
  return opt;
}
