public static ImportStatement wholePackage(String pckg){
  ImportStatement is=new ImportStatement();
  is.packageName=pckg;
  is.className="*";
  is.isStarred=true;
  return is;
}
