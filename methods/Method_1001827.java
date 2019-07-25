public Guillemet guillemet(){
  final String value=getValue("guillemet");
  return Guillemet.GUILLEMET.fromDescription(value);
}
