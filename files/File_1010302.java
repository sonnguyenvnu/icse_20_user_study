/*
 * Copyright 2003-2011 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.project.structure.modules;

import jetbrains.mps.util.Pair;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.util.io.ModelInputStream;
import jetbrains.mps.util.io.ModelOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SDependencyScope;
import org.jetbrains.mps.openapi.module.SModuleReference;

import java.io.IOException;

/**
 * Persistence and editing of SDependency
 */
public final class Dependency implements Copyable<Dependency> {
  @NotNull
  private SModuleReference myModuleRef;
  private SDependencyScope myScope = SDependencyScope.DEFAULT;
  private boolean myReexport;

  /**
   * @deprecated use the other constructors instead
   */
  @Deprecated
  @ToRemove(version = 0)
  public Dependency() {
  }

  public Dependency(SModuleReference ref, boolean reexport) {
    this(ref, SDependencyScope.DEFAULT, reexport);
  }

  public Dependency(@NotNull SModuleReference ref, @NotNull SDependencyScope scope, boolean reexport) {
    this(ref, scope);
    myReexport = reexport;
  }

  public Dependency(@NotNull SModuleReference ref, @NotNull SDependencyScope scope) {
    myModuleRef = ref;
    myScope = scope;
  }

  @NotNull
  public SModuleReference getModuleRef() {
    return myModuleRef;
  }

  public void setModuleRef(@NotNull SModuleReference moduleRef) {
    myModuleRef = moduleRef;
  }

  public boolean isReexport() {
    return myReexport;
  }

  public void setReexport(boolean reexport) {
    myReexport = reexport;
  }

  @NotNull
  public SDependencyScope getScope() {
    return myScope;
  }

  public void setScope(@NotNull SDependencyScope scope) {
    myScope = scope;
  }

  @NotNull
  public Dependency copy() {
    Dependency result = new Dependency();
    result.myModuleRef = myModuleRef;
    result.myReexport = myReexport;
    result.myScope = myScope;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof Dependency))
      return false;
    Dependency dependency = (Dependency)obj;
    return myReexport == dependency.myReexport && myScope == dependency.myScope && myModuleRef.equals(dependency.myModuleRef);
  }

  @Override
  public int hashCode() {
    return (new Pair<>(myModuleRef, myReexport)).hashCode();
  }

  public void save(ModelOutputStream stream) throws IOException {
    stream.writeByte(0x75);
    stream.writeModuleReference(myModuleRef);
    stream.writeBoolean(myReexport);
    stream.writeByte(myScope.ordinal());
  }

  public void load(ModelInputStream stream) throws IOException {
    if (stream.readByte() != 0x75) throw new IOException("bad stream: no dependency start marker");
    myModuleRef = stream.readModuleReference();
    myReexport = stream.readBoolean();
    myScope = SDependencyScope.values()[stream.readByte()];
  }

  @Override
  public String toString() {
    return String.format("{%s} %s", myScope, myModuleRef);
  }
}
