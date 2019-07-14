static double[] posixMemAlign128(int size){
  final int surplus=size % 128;
  if (surplus > 0) {
    int div=size / 128;
    return new double[(div + 1) * 128];
  }
  return new double[size];
}
