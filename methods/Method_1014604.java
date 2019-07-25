public static RegisterSet deserialize(JSONObject json){
  RegisterSet registerSet=new RegisterSet();
  JSONArray registers=(JSONArray)json.get("registers");
  for (  JSONObject jsonRegister : (ArrayList<JSONObject>)registers) {
    Register register=new Register((String)jsonRegister.get("name"));
    register.setValue((int)(long)jsonRegister.get("value"));
    registerSet.registers.put((int)(long)jsonRegister.get("index"),register);
  }
  return registerSet;
}
