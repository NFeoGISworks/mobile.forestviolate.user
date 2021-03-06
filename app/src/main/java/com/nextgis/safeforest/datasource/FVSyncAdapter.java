/*
 * Project: Forest violations
 * Purpose: Mobile application for registering facts of the forest violations.
 * Author:  Stanislav Petriakov, becomeglory@gmail.com
 * ****************************************************************************
 * Copyright (c) 2016 NextGIS, info@nextgis.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.nextgis.safeforest.datasource;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SyncResult;
import android.preference.PreferenceManager;

import com.nextgis.maplib.datasource.ngw.SyncAdapter;
import com.nextgis.maplib.map.LayerGroup;
import com.nextgis.maplib.map.MapBase;
import com.nextgis.maplib.map.NGWVectorLayer;
import com.nextgis.safeforest.util.Constants;
import com.nextgis.safeforest.util.MapUtil;
import com.nextgis.safeforest.util.SettingsConstants;

public class FVSyncAdapter extends SyncAdapter {
    public FVSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    protected void sync(LayerGroup layerGroup, String authority, SyncResult syncResult) {
        if (!MapBase.getInstance().isValid() || layerGroup.getLayerCount() == 0)
            return;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(layerGroup.getContext());
        int weeks = Integer.parseInt(preferences.getString(SettingsConstants.KEY_PREF_HISTORY, Constants.WEEKS_TO_LOAD_DATA + ""));
        MapUtil.removeOutdatedData((NGWVectorLayer) layerGroup.getLayerByName(Constants.KEY_CITIZEN_MESSAGES), weeks);
        MapUtil.removeOutdatedData((NGWVectorLayer) layerGroup.getLayerByName(Constants.KEY_FV_FOREST), weeks);
        MapUtil.removeOutdatedData((NGWVectorLayer) layerGroup.getLayerByName(Constants.KEY_FV_DOCS), weeks);
        MapUtil.removeOutdatedChanges((NGWVectorLayer) layerGroup.getLayerByName(Constants.KEY_CITIZEN_MESSAGES));
        super.sync(layerGroup, authority, syncResult);
    }
}
