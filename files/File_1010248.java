/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.classloading;

import jetbrains.mps.extapi.module.ModuleFacetBase;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.persistence.Memento;

public class DumbIdeaPluginFacet extends ModuleFacetBase implements IdeaPluginModuleFacet {
  private String pluginId;

  public DumbIdeaPluginFacet() {
    super(FACET_TYPE);
  }

  @Override
  public boolean isValid() {
    return true;
  }

  @Nullable
  @Override
  public ClassLoader getClassLoader() {
    return ClassLoaderManager.class.getClassLoader();
  }

  @Override
  public void save(Memento memento) {
    memento.put("pluginId", pluginId);
  }

  @Override
  public void load(Memento memento) {
    pluginId = memento.get("pluginId");
  }

  @Override
  public String getPluginId() {
    return pluginId;
  }
}
