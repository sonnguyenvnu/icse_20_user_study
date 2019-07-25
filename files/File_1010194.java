/*
 * Copyright 2003-2017 JetBrains s.r.o.
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
package jetbrains.mps.generator.info;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.vfs.IFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneratorPathsComponent implements CoreComponent {
  private static GeneratorPathsComponent INSTANCE;
  private final List<ForeignPathsProvider> myForeignPathsProviders = Collections.synchronizedList(new ArrayList<>());

  public GeneratorPathsComponent() {
  }

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }
    INSTANCE = this;
  }

  @Override
  public void dispose() {
    INSTANCE = null;
  }

  public boolean isForeign(final IFile path) {
    for (ForeignPathsProvider fpp : myForeignPathsProviders) {
      if (fpp.belongsToForeignPath(path) != null) {
        return true;
      }
    }
    return false;
  }

  public void registerForeignPathsProvider(ForeignPathsProvider provider) {
    myForeignPathsProviders.add(provider);
  }

  public void unregisterForeignPathsProvider(ForeignPathsProvider provider) {
    myForeignPathsProviders.remove(provider);
  }

  public static GeneratorPathsComponent getInstance() {
    return INSTANCE;
  }
}
