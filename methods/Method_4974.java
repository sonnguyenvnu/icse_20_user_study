static PrivateCommand parseFromSection(ParsableByteArray sectionData,int commandLength,long ptsAdjustment){
  long identifier=sectionData.readUnsignedInt();
  byte[] privateBytes=new byte[commandLength - 4];
  sectionData.readBytes(privateBytes,0,privateBytes.length);
  return new PrivateCommand(identifier,privateBytes,ptsAdjustment);
}
