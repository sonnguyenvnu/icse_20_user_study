@Override public int compareTo(Object o){
  if (!(o instanceof Configuration))   return hashCode() - o.hashCode();
  Configuration configuration=(Configuration)o;
  float diff=getScore(true) - configuration.getScore(true);
  if (diff > 0)   return (int)Math.ceil(diff);
 else   if (diff < 0)   return (int)Math.floor(diff);
 else   return 0;
}
