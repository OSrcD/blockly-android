/*
 * Copyright 2015 Google Inc. All Rights Reserved.
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

package com.google.blockly.demo;

import android.support.annotation.NonNull;

import com.google.blockly.BlocklySectionsActivity;
import com.google.blockly.LoggingCodeGeneratorCallback;
import com.google.blockly.MockBlocksProvider;
import com.google.blockly.NavigationDrawerFragment;
import com.google.blockly.WorkspaceFragment;
import com.google.blockly.control.BlocklyController;
import com.google.blockly.utils.CodeGenerationRequest;


/**
 * Activity with Developer oriented tests.
 */
public class DevTestsActivity extends BlocklySectionsActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private static final String TAG = "DevTestsActivity";

    public static final String WORKSPACE_FOLDER_PREFIX = "sample_sections/level_";

    protected CodeGenerationRequest.CodeGeneratorCallback mCodeGeneratorCallback =
            new LoggingCodeGeneratorCallback(this, TAG);

    private BlocklyController mController;
    protected WorkspaceFragment mWorkspaceFragment;

    /**
     * @return The asset path for the current xml toolbox config.
     */
    @Override
    protected String getWorkspaceToolboxPath() {
        // Expose a different set of blocks to the user at each level.
        return WORKSPACE_FOLDER_PREFIX + (getCurrentSection() + 1) + "/toolbox.xml";
    }

    /**
     * @return The asset path for the json block definitions.
     */
    @Override
    protected String getWorkspaceBlocksPath() {
        return "default/toolbox_blocks.json";
    }

    @Override
    protected String getStartingWorkspacePath() {
        return "default/demo_workspace.xml";
    }

    @NonNull
    @Override
    protected CodeGenerationRequest.CodeGeneratorCallback getCreateCodeGenerationCallback() {
        return null;
    }

    @NonNull
    protected String getGeneratorJsFilename() {
        return "sample_sections/generators.js";
    }

    @NonNull
    protected String getBlockDefinitionsFilename() {
        return "sample_sections/definitions.json";
    }

    @Override
    protected BlocklyController onCreateController() {
        BlocklyController controller = super.onCreateController();
        MockBlocksProvider.makeComplexModel(controller.getWorkspace());
        return controller;
    }
}
