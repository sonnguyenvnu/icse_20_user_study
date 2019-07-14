void moov(MP4Atom atom) throws IOException {
  if (LOGGER.isLoggable(debugLevel)) {
    LOGGER.log(debugLevel,atom.toString());
  }
  while (atom.hasMoreChildren()) {
    MP4Atom child=atom.nextChild();
switch (child.getType()) {
case "mvhd":
      mvhd(child);
    break;
case "trak":
  trak(child);
break;
case "udta":
udta(child);
break;
default :
break;
}
}
}
