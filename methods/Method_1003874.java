@VisibleForTesting synchronized void reset(){
  valueApplied=false;
  valueObserved=false;
  value=hasDefault ? defaultValue : null;
}
