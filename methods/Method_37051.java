protected void checkCardResolverAndMVHelper(ServiceManager serviceManager){
  if (cardResolver == null) {
    cardResolver=serviceManager.getService(CardResolver.class);
    Preconditions.checkState(cardResolver != null,"Must register CardResolver into ServiceManager first");
  }
  if (mvHelper == null) {
    mvHelper=serviceManager.getService(MVHelper.class);
    Preconditions.checkState(mvHelper != null,"Must register CellResolver into ServiceManager first");
  }
}
