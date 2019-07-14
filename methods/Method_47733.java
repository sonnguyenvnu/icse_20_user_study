private String toText(Frequency freq){
  Resources resources=getResources();
  Integer num=freq.getNumerator();
  Integer den=freq.getDenominator();
  if (num.equals(den))   return resources.getString(R.string.every_day);
  if (num == 1) {
    if (den == 7)     return resources.getString(R.string.every_week);
    if (den % 7 == 0)     return resources.getString(R.string.every_x_weeks,den / 7);
    return resources.getString(R.string.every_x_days,den);
  }
  String times_every=resources.getString(R.string.times_every);
  return String.format("%d %s %d %s",num,times_every,den,resources.getString(R.string.days));
}
