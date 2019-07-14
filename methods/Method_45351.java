protected final Option httpsCertificate(){
  Option option=new Option(null,"https",true,"Https certificate filename");
  option.setType(String.class);
  option.setRequired(false);
  return option;
}
