protected static String parseEac3SupplementalProperties(List<Descriptor> supplementalProperties){
  for (int i=0; i < supplementalProperties.size(); i++) {
    Descriptor descriptor=supplementalProperties.get(i);
    String schemeIdUri=descriptor.schemeIdUri;
    if ("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(schemeIdUri) && "ec+3".equals(descriptor.value)) {
      return MimeTypes.AUDIO_E_AC3_JOC;
    }
  }
  return MimeTypes.AUDIO_E_AC3;
}
