private static void checkKeys(final String[] keys){
  if (keys == null || keys.length < 1)   throw new IllegalArgumentException("Invalid keys");
  for (int i=0; i < keys.length; i++)   if (keys[i] == null)   throw new IllegalArgumentException("Key cannot be null at position " + i);
}
