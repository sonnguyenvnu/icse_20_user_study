@Benchmark public Alpha methodHandle_invoke(ThreadState state){
  return methodHandleFactory.invoke(state.i++);
}
