@Override public void init(){
  this.feedbackImage=new BufferedImage(windowWidth,windowHeight + additionalFeedbackHeight,BufferedImage.TYPE_INT_ARGB);
  this.feedbackGraphics=(Graphics2D)feedbackImage.getGraphics();
  this.graphImage=new BufferedImage(windowWidth,windowHeight + additionalFeedbackHeight,BufferedImage.TYPE_INT_ARGB);
  this.graphics=(Graphics2D)graphImage.getGraphics();
  bins=feedbackSettings.getBins();
  System.out.println("fb settings bins: " + bins);
  channels=feedbackSettings.getNumChannels();
  feedbackSamples=new double[windowWidth][bins][channels];
  totalFeedbackSamples=new double[windowWidth][bins][channels];
  pointer=0;
  binHeight=windowHeight / (bins + 1) - (10 * (bins + 1));
  s=255 / (bins * channels);
  stepX=windowWidth / segX;
  stepY=binHeight / segY;
}
