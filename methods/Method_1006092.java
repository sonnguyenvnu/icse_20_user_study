public void save(){
  List<BibtexString> stringsToAdd=allStrings.stream().map(this::fromBibtexStringViewModel).collect(Collectors.toList());
  bibDatabase.setStrings(stringsToAdd);
}
