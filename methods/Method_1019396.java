InetAddress choose(){
  int start=population.size() / 2;
  int index=start + random.nextInt(population.size() - start);
  InetAddress choice=population.get(index).address;
  for (  Pair p : population) {
    p.distance=Math.min(p.distance,hammingDistance(choice,p.address));
  }
  Collections.sort(population);
  return choice;
}
