private Fragment createFragmentForPosition(int index){
switch (index) {
case 0:
    return new AutoTransitionSample();
case 1:
  return new InterpolatorDurationStartDelaySample();
case 2:
return new PathMotionSample();
case 3:
return new SlideSample();
case 4:
return new ScaleSample();
case 5:
return new ExplodeAndEpicenterExample();
case 6:
return new TransitionNameSample();
case 7:
return new ImageTransformSample();
case 8:
return new RecolorSample();
case 9:
return new RotateSample();
case 10:
return new ChangeTextSample();
case 11:
return new CustomTransitionSample();
case 12:
return new ScenesSample();
}
return null;
}
