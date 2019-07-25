public static Ftile create(Ftile tile,Collection<PositionedNote> notes,ISkinParam skinParam,boolean withLink){
  if (notes.size() > 1) {
    return new FtileWithNotes(tile,notes,skinParam);
  }
  if (notes.size() == 0) {
    throw new IllegalArgumentException();
  }
  return new FtileWithNoteOpale(tile,notes.iterator().next(),skinParam,withLink);
}
