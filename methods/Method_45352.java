protected final Option keyStore(){
  Option option=new Option(null,"keystore",true,"Key store password");
  option.setType(String.class);
  option.setRequired(false);
  return option;
}
