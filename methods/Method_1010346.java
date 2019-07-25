public static void dispose(SModel model){
  SModelReference mr=model.getReference();
  FastNodeFinder finder=ourFinders.remove(mr);
  if (finder != null) {
    finder.dispose();
  }
}
