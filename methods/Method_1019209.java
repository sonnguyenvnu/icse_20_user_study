@Override public void concat(Path trg,Path[] psrcs) throws IOException {
  Path[] psrcsNew=convertDefaults(psrcs);
  fileSystem.concat(convertToDefaultPath(trg),psrcsNew);
}
