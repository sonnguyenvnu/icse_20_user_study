@Override public NinePatchInterval clone(){
  final NinePatchInterval npi=new NinePatchInterval();
  npi.setId(getId());
  npi.setPixel(isPixel());
  npi.setStart(getStart());
  npi.setEnd(getEnd());
  return npi;
}
