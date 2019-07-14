private ArrayList<byte[]> convertScoreMembersToByteArrays(final Map<byte[],Double> scoreMembers){
  ArrayList<byte[]> args=new ArrayList<byte[]>(scoreMembers.size() * 2);
  for (  Map.Entry<byte[],Double> entry : scoreMembers.entrySet()) {
    args.add(toByteArray(entry.getValue()));
    args.add(entry.getKey());
  }
  return args;
}
