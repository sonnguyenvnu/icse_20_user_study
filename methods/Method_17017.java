@Benchmark public int sparseArray(){
  return sparse[(int)Thread.currentThread().getId()];
}
