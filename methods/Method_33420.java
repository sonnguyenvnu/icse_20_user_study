public static void updateBackground(Background newBackground,Region nodeToUpdate,Paint fill){
  if (newBackground != null && !newBackground.getFills().isEmpty()) {
    final BackgroundFill[] fills=new BackgroundFill[newBackground.getFills().size()];
    for (int i=0; i < newBackground.getFills().size(); i++) {
      BackgroundFill bf=newBackground.getFills().get(i);
      fills[i]=new BackgroundFill(fill,bf.getRadii(),bf.getInsets());
    }
    nodeToUpdate.setBackground(new Background(fills));
  }
}
