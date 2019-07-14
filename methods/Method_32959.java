private void bindAction(JFXHamburger burger){
  burger.setOnMouseClicked((e) -> {
    final Transition burgerAnimation=burger.getAnimation();
    burgerAnimation.setRate(burgerAnimation.getRate() * -1);
    burgerAnimation.play();
  }
);
}
