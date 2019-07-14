@Benchmark public Alpha methodHandle_invokeExact(ThreadState state){
  return methodHandleFactory.invokeExact(state.i++);
}
