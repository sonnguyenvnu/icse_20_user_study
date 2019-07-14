protected final Option settingsOption(){
  Option opt=new Option("g",true,"global settings");
  opt.setType(String.class);
  opt.setRequired(false);
  return opt;
}
