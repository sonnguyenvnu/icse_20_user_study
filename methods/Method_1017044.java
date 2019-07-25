@Override public DurationExpression divide(final Expression other){
  final long den=other.cast(IntegerExpression.class).getValue();
  long value=this.value;
  TimeUnit unit=this.unit;
  outer:   while (value % den != 0) {
    if (unit == TimeUnit.MILLISECONDS) {
      break;
    }
    final TimeUnit next=nextSmallerUnit(unit);
    value=next.convert(value,unit);
    unit=next;
  }
  return new DurationExpression(context,unit,value / den);
}
