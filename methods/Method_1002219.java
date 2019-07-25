private void apply(Boolean oldVal,Boolean newVal,Runnable positive,Runnable negative){
  if (oldVal == null || newVal == null) {
    return;
  }
  if (!oldVal && newVal) {
    positive.run();
  }
 else   if (oldVal && !newVal) {
    negative.run();
  }
}
