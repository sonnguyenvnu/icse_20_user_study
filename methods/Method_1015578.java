protected static String print(BiConsumer<Integer,Integer> wait_strategy){
  if (wait_strategy == null)   return null;
  if (wait_strategy == SPIN)   return "spin";
 else   if (wait_strategy == YIELD)   return "yield";
 else   if (wait_strategy == PARK)   return "park";
 else   if (wait_strategy == SPIN_PARK)   return "spin-park";
 else   if (wait_strategy == SPIN_YIELD)   return "spin-yield";
 else   return wait_strategy.getClass().getSimpleName();
}
