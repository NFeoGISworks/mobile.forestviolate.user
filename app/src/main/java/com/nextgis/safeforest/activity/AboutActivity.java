/*
 * Project: Forest violations
 * Purpose: Mobile application for registering facts of the forest violations.
 * Author:  Dmitry Baryshnikov (aka Bishop), bishop.dev@gmail.com
 * *****************************************************************************
 * Copyright (c) 2015-2015. NextGIS, info@nextgis.com
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

package com.nextgis.safeforest.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.nextgis.maplibui.activity.NGActivity;
import com.nextgis.safeforest.R;


public class AboutActivity extends NGActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        setToolbar(R.id.main_toolbar);

        TextView txtVersion = (TextView) findViewById(R.id.app_version);
        try {
            String pkgName = this.getPackageName();
            PackageManager pm = this.getPackageManager();
            String versionName = pm.getPackageInfo(pkgName, 0).versionName;
            String versionCode =
                    Integer.toString(pm.getPackageInfo(this.getPackageName(), 0).versionCode);
            txtVersion.setText("v. " + versionName + " (rev. " + versionCode + ")");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            txtVersion.setText("");
        }

        TextView txtCreditsText = (TextView) findViewById(R.id.credits);
        txtCreditsText.setText(Html.fromHtml(getString(R.string.credits)));
        txtCreditsText.setMovementMethod(LinkMovementMethod.getInstance());

        TextView txtCopyrightText = (TextView) findViewById(R.id.copyright);
        txtCopyrightText.setText(Html.fromHtml(getString(R.string.copyright)));
        txtCopyrightText.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
