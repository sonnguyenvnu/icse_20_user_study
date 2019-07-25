public static Optional<MetaDataDiff> compare(MetaData originalMetaData,MetaData newMetaData){
  if (originalMetaData.equals(newMetaData)) {
    return Optional.empty();
  }
 else {
    return Optional.of(new MetaDataDiff(originalMetaData,newMetaData));
  }
}
