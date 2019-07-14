/** 
 * Builds a GL shader program from vertex & fragment shader code.
 * @param vertexCode GLES20 vertex shader program as arrays of strings. Strings are joined byadding a new line character in between each of them.
 * @param fragmentCode GLES20 fragment shader program as arrays of strings. Strings are joined byadding a new line character in between each of them.
 * @return GLES20 program id.
 */
public static int compileProgram(String[] vertexCode,String[] fragmentCode){
  return compileProgram(TextUtils.join("\n",vertexCode),TextUtils.join("\n",fragmentCode));
}
