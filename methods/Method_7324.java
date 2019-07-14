protected FileTypeBox createFileTypeBox(){
  LinkedList<String> minorBrands=new LinkedList<>();
  minorBrands.add("isom");
  minorBrands.add("iso2");
  minorBrands.add("avc1");
  minorBrands.add("mp41");
  return new FileTypeBox("isom",512,minorBrands);
}
