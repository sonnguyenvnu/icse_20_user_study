/** 
 * Writes the given class to a respective file in the configuration package
 * @param filer    filer to write to
 * @param typeSpec the class
 * @throws IOException if writing fails
 */
public static void writeClass(@NonNull Filer filer,@NonNull TypeSpec typeSpec) throws IOException {
  JavaFile.builder(PACKAGE,typeSpec).skipJavaLangImports(true).indent("    ").addFileComment("Copyright (c) " + Calendar.getInstance().get(Calendar.YEAR) + "\n\n" + "Licensed under the Apache License, Version 2.0 (the \"License\");\n" + "you may not use this file except in compliance with the License.\n\n" + "http://www.apache.org/licenses/LICENSE-2.0\n\n" + "Unless required by applicable law or agreed to in writing, software\n" + "distributed under the License is distributed on an \"AS IS\" BASIS,\n" + "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" + "See the License for the specific language governing permissions and\n" + "limitations under the License.").build().writeTo(filer);
}
