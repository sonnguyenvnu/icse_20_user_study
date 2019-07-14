protected String getFileManagerLabel(){
switch (PlatformService.getInstance().getOs()) {
case Linux:
    return "your file manager";
case MacOSX:
  return "the Finder";
default :
return "Explorer";
}
}
