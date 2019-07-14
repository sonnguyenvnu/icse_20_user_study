protected void createStsz(Track track,SampleTableBox stbl){
  SampleSizeBox stsz=new SampleSizeBox();
  stsz.setSampleSizes(track2SampleSizes.get(track));
  stbl.addBox(stsz);
}
