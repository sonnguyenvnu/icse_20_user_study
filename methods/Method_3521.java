public NShortSegment enableAllNamedEntityRecognize(boolean enable){
  config.nameRecognize=enable;
  config.japaneseNameRecognize=enable;
  config.translatedNameRecognize=enable;
  config.placeRecognize=enable;
  config.organizationRecognize=enable;
  config.updateNerConfig();
  return this;
}
