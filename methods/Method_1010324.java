public static SContainmentLinkAdapter deserialize(String s){
  if (s.startsWith(SContainmentLinkAdapterById.LINK_PREFIX)) {
    return SContainmentLinkAdapterById.deserialize(s);
  }
 else   if (s.startsWith(InvalidContainmentLink.INVALID_PREFIX)) {
    return InvalidContainmentLink.deserialize(s);
  }
 else {
    throw new FormatException("Illegal link type: " + s);
  }
}
