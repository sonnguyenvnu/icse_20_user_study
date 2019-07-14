protected void hookInstrumentationAndHandler(){
  try {
    ActivityThread activityThread=ActivityThread.currentActivityThread();
    Instrumentation baseInstrumentation=activityThread.getInstrumentation();
    final VAInstrumentation instrumentation=createInstrumentation(baseInstrumentation);
    Reflector.with(activityThread).field("mInstrumentation").set(instrumentation);
    Handler mainHandler=Reflector.with(activityThread).method("getHandler").call();
    Reflector.with(mainHandler).field("mCallback").set(instrumentation);
    this.mInstrumentation=instrumentation;
    Log.d(TAG,"hookInstrumentationAndHandler succeed : " + mInstrumentation);
  }
 catch (  Exception e) {
    Log.w(TAG,e);
  }
}
