private void loadSurnameDict(){
  DictSegment _SurnameDict=new DictSegment((char)0);
  Path file=PathUtils.get(getDictRoot(),Dictionary.PATH_DIC_SURNAME);
  loadDictFile(_SurnameDict,file,true,"Surname");
}
