private void onContainerAtomRead(ContainerAtom container) throws ParserException {
  if (container.type == Atom.TYPE_moov) {
    onMoovContainerAtomRead(container);
  }
 else   if (container.type == Atom.TYPE_moof) {
    onMoofContainerAtomRead(container);
  }
 else   if (!containerAtoms.isEmpty()) {
    containerAtoms.peek().add(container);
  }
}
