private static boolean passesMountIndexBisect(int mountIndex){
  return mountIndex >= ComponentsConfiguration.mountIndexBisectStart && mountIndex <= ComponentsConfiguration.mountIndexBisectEnd;
}
