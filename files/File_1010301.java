/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.project.structure.model;

import jetbrains.mps.extapi.persistence.FileBasedModelRoot;
import jetbrains.mps.persistence.MementoImpl;
import jetbrains.mps.persistence.PersistenceRegistry;
import jetbrains.mps.project.structure.modules.Copyable;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.util.io.MementoStreamUtil;
import jetbrains.mps.util.io.ModelInputStream;
import jetbrains.mps.util.io.ModelOutputStream;
import jetbrains.mps.vfs.IFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Immutable;
import org.jetbrains.mps.openapi.persistence.Memento;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Immutable
public final class ModelRootDescriptor implements Copyable<ModelRootDescriptor> {
  private static final int MODEL_ROOT_START_MARKER = 0x6a;
  private final String myType;
  private final Memento myMemento;

  public ModelRootDescriptor() {
    myType = null;
    myMemento = new MementoImpl();
  }

  public ModelRootDescriptor(String type, Memento memento) {
    myType = type;
    myMemento = memento;
  }

  public String getType() {
    if (myType == null) {
      return getMemento().getChild("manager") != null ? PersistenceRegistry.OBSOLETE_MODEL_ROOT : PersistenceRegistry.DEFAULT_MODEL_ROOT;
    }
    return myType;
  }

  public Memento getMemento() {
    return myMemento;
  }

  public void save(@NotNull ModelOutputStream stream) throws IOException {
    stream.writeByte(MODEL_ROOT_START_MARKER);
    stream.writeString(myType);
    MementoStreamUtil.writeMemento(null, myMemento, stream);
  }

  @NotNull
  public static ModelRootDescriptor load(@NotNull ModelInputStream stream) throws IOException {
    if (stream.readByte() != MODEL_ROOT_START_MARKER) {
      throw new IOException("bad stream: no model root descriptor start marker");
    }
    return new ModelRootDescriptor(stream.readString(), MementoStreamUtil.readMemento(null, stream));
  }

  @NotNull
  @Override
  public ModelRootDescriptor copy() {
    return new ModelRootDescriptor(myType, myMemento.copy());
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ModelRootDescriptor)) {
      return false;
    }

    ModelRootDescriptor modelRootDescriptor = (ModelRootDescriptor) obj;

    boolean equals = myType == null ? modelRootDescriptor.myType == null : myType.equals(modelRootDescriptor.myType);
    equals = equals && myMemento.equals(modelRootDescriptor.myMemento);
    return equals;
  }

  @Override
  public int hashCode() {
    return (myType != null ? myType.hashCode() : 0) + 17 * myMemento.hashCode();
  }

  public static ModelRootDescriptor addSourceRoot(IFile file) {
    return addSourceRoot(file, Collections.emptyList());
  }

  /**
   * @return {@code null} if one of supplied descriptors has been updated with the path, or new descriptor if none matched
   */
  @Nullable
  public static ModelRootDescriptor addSourceRoot(IFile file, final Collection<ModelRootDescriptor> modelRootDescriptors) {
    String path = file.getParent().getPath();

    for (ModelRootDescriptor descriptor : modelRootDescriptors) {
      if (descriptor.myMemento.get(FileBasedModelRoot.CONTENT_PATH).equals(path)) {
        Memento child = descriptor.myMemento.createChild(FileBasedModelRoot.SOURCE_ROOTS);
        child.put(FileBasedModelRoot.LOCATION, file.getName());
        return null;
      }
    }

    Memento m = new MementoImpl();
    m.put(FileBasedModelRoot.CONTENT_PATH, path);
    Memento child = m.createChild(FileBasedModelRoot.SOURCE_ROOTS);
    child.put(FileBasedModelRoot.LOCATION, file.getName());
    return new ModelRootDescriptor(PersistenceRegistry.JAVA_CLASSES_ROOT, m);
  }

  @Deprecated
  @ToRemove(version = 2019.2)
  @Nullable
  //use addSourceRoot instead
  public static ModelRootDescriptor getJavaStubsModelRoot(IFile file, final Collection<ModelRootDescriptor> modelRootDescriptors) {
    return addSourceRoot(file, modelRootDescriptors);
  }

  @Deprecated
  @ToRemove(version = 2019.2)
  @Nullable
  //use addSourceRoot instead
  public static ModelRootDescriptor getJavaStubsModelRoot(IFile file) {
    return addSourceRoot(file);
  }
}
