public double quant(double q){
  if (!isSorted)   sortx=sort();
  if (q > 1 || q < 0)   return (0);
 else {
    double index=(n + 1) * q;
    if (index - (int)index == 0)     return sortx[(int)index - 1];
 else     return q * sortx[(int)Math.floor(index) - 1] + (1 - q) * sortx[(int)Math.ceil(index) - 1];
  }
}
