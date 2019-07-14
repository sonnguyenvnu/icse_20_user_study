private Mode promptForMode(final File sketch,final ModeInfo preferredMode){
  final String extension=sketch.getName().substring(sketch.getName().lastIndexOf('.') + 1);
  final List<Mode> possibleModes=new ArrayList<>();
  for (  final Mode mode : getModeList()) {
    if (mode.canEdit(sketch)) {
      possibleModes.add(mode);
    }
  }
  if (possibleModes.size() == 1 && possibleModes.get(0).getIdentifier().equals(getDefaultModeIdentifier())) {
    return possibleModes.get(0);
  }
  if (possibleModes.size() == 0) {
    if (preferredMode == null) {
      final String msg="I don't know how to open a sketch with the \"" + extension + "\"\n" + "file extension. You'll have to install a different\n" + "Mode for that.";
      Messages.showWarning("Modeless Dialog",msg);
    }
 else {
      Messages.showWarning("Modeless Dialog","Install " + preferredMode.title + " Mode " + "to open this sketch.");
    }
    return null;
  }
  final Mode[] modes=possibleModes.toArray(new Mode[possibleModes.size()]);
  final String message=preferredMode == null ? (nextMode.getTitle() + " Mode can't open ." + extension + " files, " + "but you have one or more modes\ninstalled that can. " + "Would you like to try one?") : ("That's a " + preferredMode.title + " Mode sketch, " + "but you don't have " + preferredMode.title + " installed.\n" + "Would you like to try a different mode for opening a " + "." + extension + " sketch?");
  return (Mode)JOptionPane.showInputDialog(null,message,"Choose Wisely",JOptionPane.QUESTION_MESSAGE,null,modes,modes[0]);
}
