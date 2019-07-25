@Override public Node description(){
  return new Label(toString() + '.' + (changedGroups == null ? "" : ' ' + Localization.lang("Accepting the change replaces the complete groups tree with the externally modified groups tree.")));
}
