protected void consumeUniforms(){
  if (uniformValues != null && 0 < uniformValues.size()) {
    int unit=0;
    for (    String name : uniformValues.keySet()) {
      int loc=getUniformLoc(name);
      if (loc == -1) {
        PGraphics.showWarning("The shader doesn't have a uniform called \"" + name + "\" OR the uniform was removed during " + "compilation because it was unused.");
        continue;
      }
      UniformValue val=uniformValues.get(name);
      if (val.type == UniformValue.INT1) {
        int[] v=((int[])val.value);
        pgl.uniform1i(loc,v[0]);
      }
 else       if (val.type == UniformValue.INT2) {
        int[] v=((int[])val.value);
        pgl.uniform2i(loc,v[0],v[1]);
      }
 else       if (val.type == UniformValue.INT3) {
        int[] v=((int[])val.value);
        pgl.uniform3i(loc,v[0],v[1],v[2]);
      }
 else       if (val.type == UniformValue.INT4) {
        int[] v=((int[])val.value);
        pgl.uniform4i(loc,v[0],v[1],v[2],v[3]);
      }
 else       if (val.type == UniformValue.FLOAT1) {
        float[] v=((float[])val.value);
        pgl.uniform1f(loc,v[0]);
      }
 else       if (val.type == UniformValue.FLOAT2) {
        float[] v=((float[])val.value);
        pgl.uniform2f(loc,v[0],v[1]);
      }
 else       if (val.type == UniformValue.FLOAT3) {
        float[] v=((float[])val.value);
        pgl.uniform3f(loc,v[0],v[1],v[2]);
      }
 else       if (val.type == UniformValue.FLOAT4) {
        float[] v=((float[])val.value);
        pgl.uniform4f(loc,v[0],v[1],v[2],v[3]);
      }
 else       if (val.type == UniformValue.INT1VEC) {
        int[] v=((int[])val.value);
        updateIntBuffer(v);
        pgl.uniform1iv(loc,v.length,intBuffer);
      }
 else       if (val.type == UniformValue.INT2VEC) {
        int[] v=((int[])val.value);
        updateIntBuffer(v);
        pgl.uniform2iv(loc,v.length / 2,intBuffer);
      }
 else       if (val.type == UniformValue.INT3VEC) {
        int[] v=((int[])val.value);
        updateIntBuffer(v);
        pgl.uniform3iv(loc,v.length / 3,intBuffer);
      }
 else       if (val.type == UniformValue.INT4VEC) {
        int[] v=((int[])val.value);
        updateIntBuffer(v);
        pgl.uniform4iv(loc,v.length / 4,intBuffer);
      }
 else       if (val.type == UniformValue.FLOAT1VEC) {
        float[] v=((float[])val.value);
        updateFloatBuffer(v);
        pgl.uniform1fv(loc,v.length,floatBuffer);
      }
 else       if (val.type == UniformValue.FLOAT2VEC) {
        float[] v=((float[])val.value);
        updateFloatBuffer(v);
        pgl.uniform2fv(loc,v.length / 2,floatBuffer);
      }
 else       if (val.type == UniformValue.FLOAT3VEC) {
        float[] v=((float[])val.value);
        updateFloatBuffer(v);
        pgl.uniform3fv(loc,v.length / 3,floatBuffer);
      }
 else       if (val.type == UniformValue.FLOAT4VEC) {
        float[] v=((float[])val.value);
        updateFloatBuffer(v);
        pgl.uniform4fv(loc,v.length / 4,floatBuffer);
      }
 else       if (val.type == UniformValue.MAT2) {
        float[] v=((float[])val.value);
        updateFloatBuffer(v);
        pgl.uniformMatrix2fv(loc,1,false,floatBuffer);
      }
 else       if (val.type == UniformValue.MAT3) {
        float[] v=((float[])val.value);
        updateFloatBuffer(v);
        pgl.uniformMatrix3fv(loc,1,false,floatBuffer);
      }
 else       if (val.type == UniformValue.MAT4) {
        float[] v=((float[])val.value);
        updateFloatBuffer(v);
        pgl.uniformMatrix4fv(loc,1,false,floatBuffer);
      }
 else       if (val.type == UniformValue.SAMPLER2D) {
        PImage img=(PImage)val.value;
        Texture tex=currentPG.getTexture(img);
        if (textures == null)         textures=new HashMap<Integer,Texture>();
        textures.put(loc,tex);
        if (texUnits == null)         texUnits=new HashMap<Integer,Integer>();
        if (texUnits.containsKey(loc)) {
          unit=texUnits.get(loc);
          pgl.uniform1i(loc,unit);
        }
 else {
          texUnits.put(loc,unit);
          pgl.uniform1i(loc,unit);
        }
        unit++;
      }
    }
    uniformValues.clear();
  }
}
