@ManagedOperation public String dump(){
  StringBuilder sb=new StringBuilder();
  if (l1_cache != null) {
    sb.append("L1 cache:\n").append(l1_cache.dump());
  }
  sb.append("\nL2 cache:\n").append(l2_cache.dump());
  return sb.toString();
}
