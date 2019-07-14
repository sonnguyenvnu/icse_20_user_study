protected static int parseCea708AccessibilityChannel(List<Descriptor> accessibilityDescriptors){
  for (int i=0; i < accessibilityDescriptors.size(); i++) {
    Descriptor descriptor=accessibilityDescriptors.get(i);
    if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
      Matcher accessibilityValueMatcher=CEA_708_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
      if (accessibilityValueMatcher.matches()) {
        return Integer.parseInt(accessibilityValueMatcher.group(1));
      }
 else {
        Log.w(TAG,"Unable to parse CEA-708 service block number from: " + descriptor.value);
      }
    }
  }
  return Format.NO_VALUE;
}
