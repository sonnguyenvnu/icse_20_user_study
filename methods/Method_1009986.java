@Override public double cdf(double x){
  mergeNewValues();
  if (lastUsedCell == 0) {
    return Double.NaN;
  }
 else   if (lastUsedCell == 1) {
    double width=max - min;
    if (x < min) {
      return 0;
    }
 else     if (x > max) {
      return 1;
    }
 else     if (x - min <= width) {
      return 0.5;
    }
 else {
      return (x - min) / (max - min);
    }
  }
 else {
    int n=lastUsedCell;
    if (x < min) {
      return 0;
    }
    if (x > max) {
      return 1;
    }
    if (x < mean[0]) {
      if (mean[0] - min > 0) {
        if (x == min) {
          return 0.5 / totalWeight;
        }
 else {
          return (1 + (x - min) / (mean[0] - min) * (weight[0] / 2 - 1)) / totalWeight;
        }
      }
 else {
        return 0;
      }
    }
    assert x >= mean[0];
    if (x > mean[n - 1]) {
      if (max - mean[n - 1] > 0) {
        if (x == max) {
          return 1 - 0.5 / totalWeight;
        }
 else {
          double dq=(1 + (max - x) / (max - mean[n - 1]) * (weight[n - 1] / 2 - 1)) / totalWeight;
          return 1 - dq;
        }
      }
 else {
        return 1;
      }
    }
    double weightSoFar=0;
    for (int it=0; it < n - 1; it++) {
      if (mean[it] == x) {
        double dw=0;
        while (it < n && mean[it] == x) {
          dw+=weight[it];
          it++;
        }
        return (weightSoFar + dw / 2) / totalWeight;
      }
 else       if (mean[it] <= x && x < mean[it + 1]) {
        if (mean[it + 1] - mean[it] > 0) {
          double leftExcludedW=0;
          double rightExcludedW=0;
          if (weight[it] == 1) {
            if (weight[it + 1] == 1) {
              return (weightSoFar + 1) / totalWeight;
            }
 else {
              leftExcludedW=0.5;
            }
          }
 else           if (weight[it + 1] == 1) {
            rightExcludedW=0.5;
          }
          double dw=(weight[it] + weight[it + 1]) / 2;
          assert dw > 1;
          assert (leftExcludedW + rightExcludedW) <= 0.5;
          double left=mean[it];
          double right=mean[it + 1];
          double dwNoSingleton=dw - leftExcludedW - rightExcludedW;
          assert dwNoSingleton > dw / 2;
          assert right - left > 0;
          double base=weightSoFar + weight[it] / 2 + leftExcludedW;
          return (base + dwNoSingleton * (x - left) / (right - left)) / totalWeight;
        }
 else {
          double dw=(weight[it] + weight[it + 1]) / 2;
          return (weightSoFar + dw) / totalWeight;
        }
      }
 else {
        weightSoFar+=weight[it];
      }
    }
    if (x == mean[n - 1]) {
      return 1 - 0.5 / totalWeight;
    }
 else {
      throw new IllegalStateException("Can't happen ... loop fell through");
    }
  }
}
