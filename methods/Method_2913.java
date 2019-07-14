private static void save_map(Map<Integer,Integer> map,DataOutputStream out) throws IOException {
  out.writeInt(map.size());
  for (  Map.Entry<Integer,Integer> entry : map.entrySet()) {
    out.writeInt(entry.getKey());
    out.writeInt(entry.getValue());
  }
}
