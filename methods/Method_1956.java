@Override public void initInstrumentation(final String tag,PerfListener perfListener){
  mInstrumentation.init(tag,perfListener);
  mInstrumentation.onStart();
}
