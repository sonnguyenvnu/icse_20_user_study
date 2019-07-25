@Override public void update(final Double value){
switch (mode_) {
case Sum:
    value_+=value.doubleValue();
  break;
case Min:
if (value < value_) {
  value_=value;
}
break;
case Max:
if (value > value_) {
value_=value;
}
break;
}
}
