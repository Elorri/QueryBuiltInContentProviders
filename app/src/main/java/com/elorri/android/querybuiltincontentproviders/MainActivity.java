package com.elorri.android.querybuiltincontentproviders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Since it is impossible to write into assets files because apk are packed and therefore
        // not expandable in size. I will write to external storage to be sure I can access the
        // file from my phone and send it on drop box or via email.

        File providersOverviewDir=FileUtils.getPublicAppDir(this,"AppDir" );
        FileUtils.deleteFiles(providersOverviewDir);
        File providersOverview=FileUtils.createFile(providersOverviewDir, new File
                (providersOverviewDir, "providersOverview.txt"));


        try(FileWriter fw = new FileWriter(providersOverview, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println("the text");
            //more code
            out.println("more text");
            //more code
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
