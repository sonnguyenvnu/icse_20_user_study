@Override public void onViewCreated(View view,@Nullable Bundle savedInstanceState){
  ImageView imageView=(ImageView)view.findViewById(R.id.animation_container);
  AnimationBackend animationBackend=AnimationBackendUtils.wrapAnimationBackendWithInactivityCheck(getContext(),ExampleBitmapAnimationFactory.createColorBitmapAnimationBackend(SampleData.COLORS,300,new NoOpCache()));
  final AnimatedDrawable2 animatedDrawable=new AnimatedDrawable2(animationBackend);
  imageView.setImageDrawable(animatedDrawable);
  imageView.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      if (animatedDrawable.isRunning()) {
        animatedDrawable.stop();
      }
 else {
        animatedDrawable.start();
      }
    }
  }
);
  animatedDrawable.start();
}
