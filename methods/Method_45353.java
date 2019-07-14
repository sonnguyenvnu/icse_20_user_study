protected final Option cert(){
  Option option=new Option(null,"cert",true,"Cert password");
  option.setType(String.class);
  option.setRequired(false);
  return option;
}
