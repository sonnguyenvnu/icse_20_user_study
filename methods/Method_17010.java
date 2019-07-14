@Benchmark public Alpha reflection(ThreadState state){
  return reflectionFactory.newInstance(state.i++);
}
