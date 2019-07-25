public void init() throws Exception {
  boolean min_credits_set=min_credits != 0;
  if (!min_credits_set)   min_credits=(long)(max_credits * min_threshold);
}
