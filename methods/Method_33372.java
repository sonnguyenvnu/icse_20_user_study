public void init(Runnable createPromptNodeRunnable,Node... cachedNodes){
  animatedPromptTextFill=new SimpleObjectProperty<>(promptTextFill.get());
  usePromptText=Bindings.createBooleanBinding(this::usePromptText,valueProperty,promptTextProperty,control.labelFloatProperty(),promptTextFill);
  line.setManaged(false);
  line.getStyleClass().add("input-line");
  line.setBackground(new Background(new BackgroundFill(control.getUnFocusColor(),CornerRadii.EMPTY,Insets.EMPTY)));
  focusedLine.setManaged(false);
  focusedLine.getStyleClass().add("input-focused-line");
  focusedLine.setBackground(new Background(new BackgroundFill(control.getFocusColor(),CornerRadii.EMPTY,Insets.EMPTY)));
  focusedLine.setOpacity(0);
  focusedLine.getTransforms().add(scale);
  if (usePromptText.get()) {
    createPromptNodeRunnable.run();
  }
  usePromptText.addListener(observable -> {
    createPromptNodeRunnable.run();
    control.requestLayout();
  }
);
  final Supplier<WritableValue<Number>> promptTargetSupplier=() -> promptTextSupplier.get() == null ? null : promptTextSupplier.get().translateYProperty();
  focusTimer=new JFXAnimationTimer(new JFXKeyFrame(Duration.millis(1),JFXKeyValue.builder().setTarget(focusedLine.opacityProperty()).setEndValue(1).setInterpolator(Interpolator.EASE_BOTH).setAnimateCondition(() -> control.isFocused()).build()),new JFXKeyFrame(Duration.millis(160),JFXKeyValue.builder().setTarget(scale.xProperty()).setEndValue(1).setInterpolator(Interpolator.EASE_BOTH).setAnimateCondition(() -> control.isFocused()).build(),JFXKeyValue.builder().setTarget(animatedPromptTextFill).setEndValueSupplier(() -> control.getFocusColor()).setInterpolator(Interpolator.EASE_BOTH).setAnimateCondition(() -> control.isFocused() && control.isLabelFloat()).build(),JFXKeyValue.builder().setTargetSupplier(promptTargetSupplier).setEndValueSupplier(() -> -contentHeight).setAnimateCondition(() -> control.isLabelFloat()).setInterpolator(Interpolator.EASE_BOTH).build(),JFXKeyValue.builder().setTarget(promptTextScale.xProperty()).setEndValue(0.85).setAnimateCondition(() -> control.isLabelFloat()).setInterpolator(Interpolator.EASE_BOTH).build(),JFXKeyValue.builder().setTarget(promptTextScale.yProperty()).setEndValue(0.85).setAnimateCondition(() -> control.isLabelFloat()).setInterpolator(Interpolator.EASE_BOTH).build()));
  unfocusTimer=new JFXAnimationTimer(new JFXKeyFrame(Duration.millis(160),JFXKeyValue.builder().setTargetSupplier(promptTargetSupplier).setEndValue(0).setInterpolator(Interpolator.EASE_BOTH).build(),JFXKeyValue.builder().setTarget(promptTextScale.xProperty()).setEndValue(1).setInterpolator(Interpolator.EASE_BOTH).build(),JFXKeyValue.builder().setTarget(promptTextScale.yProperty()).setEndValue(1).setInterpolator(Interpolator.EASE_BOTH).build()));
  promptContainer.setManaged(false);
  promptContainer.setMouseTransparent(true);
  clip.setSmooth(false);
  clip.setX(0);
  clip.widthProperty().bind(promptContainer.widthProperty());
  promptContainer.setClip(clip);
  focusTimer.setOnFinished(() -> animating=false);
  unfocusTimer.setOnFinished(() -> animating=false);
  focusTimer.setCacheNodes(cachedNodes);
  unfocusTimer.setCacheNodes(cachedNodes);
  control.focusedProperty().addListener(observable -> {
    if (control.isFocused()) {
      focus();
    }
 else {
      unFocus();
    }
  }
);
  promptTextFill.addListener(observable -> {
    if (!control.isLabelFloat() || !control.isFocused()) {
      animatedPromptTextFill.set(promptTextFill.get());
    }
  }
);
  updateDisabled();
}
