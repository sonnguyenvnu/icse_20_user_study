private static void Finished(String name){
  if (Parameters.Debug) {
    Debug.printElapsedTime(start,name + " finished in");
  }
}
