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
package jetbrains.mps.plugins;

import jetbrains.mps.plugins.applicationplugins.BaseApplicationPlugin;
import jetbrains.mps.plugins.projectplugins.BaseProjectPlugin;
import org.jetbrains.annotations.Nullable;

/**
 * hashCode() and equals() must be overridden for PluginContributor!
 */
public class PluginContributor extends AbstractPluginFactory {
  @Nullable
  public BaseProjectPlugin createProjectPlugin() {
    return null;
  }

  @Nullable
  public BaseApplicationPlugin createApplicationPlugin() {
    return null;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T create(Class<T> aClass) {
    if (BaseProjectPlugin.class == aClass) {
      return (T) createProjectPlugin();
    } else if (BaseApplicationPlugin.class == aClass) {
      return (T) createApplicationPlugin();
    }
    throw new IllegalArgumentException("Can't create instance: " + aClass);
  }
}
