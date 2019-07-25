@Override public Diff<MetaData.Custom> diff(MetaData.Custom before){
  return new IngestMetadataDiff((IngestMetadata)before,this);
}
