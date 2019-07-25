/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.extapi.persistence;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.persistence.DataSource;
import org.jetbrains.mps.openapi.persistence.DataSourceListener;

/**
 * Trivial {@link org.jetbrains.mps.openapi.persistence.DataSourceListener} implementation that tracks
 * source's timestamp and triggers model reload
 * @author Artem Tikhomirov
 */
public class ModelSourceChangeTracker implements DataSourceListener {
  protected final ReloadCallback myCallback;
  private long mySourceTimestamp = -1;

  public ModelSourceChangeTracker(@NotNull ReloadCallback callback) {
    myCallback = callback;
  }

  public void attach(@NotNull SModel model) {
    model.getSource().addListener(this);
    // perhaps, caller shall initialize timestamp according to own needs. Done here as this is the way it was in ReloadableSModelBase
    updateTimestamp(model.getSource());
  }

  public void detach(@NotNull SModel model) {
    model.getSource().removeListener(this);
  }

  public long getTimestamp() {
    return mySourceTimestamp;
  }


  // the reason why these methods take DataSource is that I didn't want to keep SModel, although this is not necessarily the best approach
  // and keeping the model we are attached to might be reasonable
  public void updateTimestamp(@NotNull DataSource dataSource) {
    mySourceTimestamp = dataSource.getTimestamp();
  }

  public boolean needsReloading(@NotNull DataSource dataSource) {
    return dataSource.getTimestamp() != mySourceTimestamp;
  }

  @Override
  public void changed(DataSource source) {
    if (needsReloading(source)) {
      myCallback.reloadFromDiskSafe();
    }
  }

  public interface ReloadCallback {
    /*
     *  Shall resolve storage/memory conflicts if any.
     */
    void reloadFromDiskSafe();
  }
}
