public void convert(String rendered,Consumer<String>... nextStep){
  threadService.runActionLater(() -> {
    this.rendered=rendered;
    String url=String.format(slideUrl,controller.getPort(),directoryService.interPath());
    if (controller.rightShowerHider.getShowing().orElse(null) != slidePane || !url.equals(slidePane.getLocation())) {
      slidePane.load(url);
    }
 else {
      threadService.runActionLater(() -> {
        slidePane.replaceSlides(rendered);
      }
);
    }
    for (    Consumer<String> step : nextStep) {
      step.accept(rendered);
    }
  }
);
}
