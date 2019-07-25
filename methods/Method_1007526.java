public static void exit(Object aExitValue,Fiber f){
  assert f.pc == 0 : "f.pc != 0";
  f.task.setPauseReason(new TaskDoneReason(aExitValue));
  f.togglePause();
}
