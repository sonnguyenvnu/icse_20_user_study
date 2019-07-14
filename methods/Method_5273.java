protected static int parseCea608AccessibilityChannel(List<Descriptor> accessibilityDescriptors){
  for (int i=0; i < accessibilityDescriptors.size(); i++) {
    Descriptor descriptor=accessibilityDescriptors.get(i);
    if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
      Matcher accessibilityValueMatcher=CEA_608_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
      if (accessibilityValueMatcher.matches()) {
        return Integer.parseInt(accessibilityValueMatcher.group(1));
      }
 else {
        Log.w(TAG,"Unable to parse CEA-608 channel number from: " + descriptor.value);
      }
    }
  }
  return Format.NO_VALUE;
}
