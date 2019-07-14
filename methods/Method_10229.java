public static void fun3(ScriptObjectMirror mirror){
  System.out.println(mirror.getClassName() + ": " + Arrays.toString(mirror.getOwnKeys(true)));
}
